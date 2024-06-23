package com.pnu.lab.control.labcontrol.service;

import com.pnu.lab.control.labcontrol.api.dto.ChangeMemberTypeRequest;
import com.pnu.lab.control.labcontrol.api.dto.CoursePreviewDto;
import com.pnu.lab.control.labcontrol.api.dto.CourseUserPreviewDto;
import com.pnu.lab.control.labcontrol.api.dto.UserCourseListRequest;
import com.pnu.lab.control.labcontrol.domain.Course;
import com.pnu.lab.control.labcontrol.domain.CourseMember;
import com.pnu.lab.control.labcontrol.exception.EntityNotFoundException;
import com.pnu.lab.control.labcontrol.repository.BaseSearchRepository;
import com.pnu.lab.control.labcontrol.repository.CourseMemberRepository;
import com.pnu.lab.control.labcontrol.repository.CourseRepository;
import com.pnu.lab.control.labcontrol.service.event.UserLeaveCourseEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService extends AbstractSearchService<Course> {

    private final CourseRepository repository;
    private final CourseMemberRepository courseMemberRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    public List<CourseUserPreviewDto> getCourseMembers(String id) {
        return courseMemberRepository.getCourseMembers(id);
    }

    public List<CoursePreviewDto> getUserCourseList(UserCourseListRequest request) {
        return repository.getUserCourseList(request);
    }

    public List<CoursePreviewDto> getCourseListByOwner(String ownerId) {
        return repository.getCourseListByOwner(ownerId);
    }

    public CourseMember addCourseMember(String id, CourseMember courseMember) {
        courseMember.setCourseId(id);
        return courseMemberRepository.save(courseMember);
    }

    public void changeMemberType(String id, ChangeMemberTypeRequest request) {
        courseMemberRepository
                .findByCourseIdAndUserId(id, request.getUserId())
                .map(courseMember -> {
                    courseMember.setMemberType(request.getMemberType());
                    return courseMember;
                })
                .ifPresentOrElse(courseMemberRepository::save, () -> {
                    throw new EntityNotFoundException("User with id %s is not member of the course %s".formatted(request.getUserId(), id));
                });
    }

    public void deleteCourseMember(String id, String userId) {
        courseMemberRepository.deleteByCourseIdAndUserId(id, userId);
        applicationEventPublisher.publishEvent(new UserLeaveCourseEvent(id, userId));
    }

    @Override
    public void delete(Course course) {
        courseMemberRepository.deleteByCourseId(course.getId());
        super.delete(course);
    }

    @Override
    public Class<Course> getType() {
        return Course.class;
    }

    @Override
    public BaseSearchRepository<Course> getRepository() {
        return repository;
    }
}
