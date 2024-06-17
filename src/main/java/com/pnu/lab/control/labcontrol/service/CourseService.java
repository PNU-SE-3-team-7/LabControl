package com.pnu.lab.control.labcontrol.service;

import com.pnu.lab.control.labcontrol.domain.Course;
import com.pnu.lab.control.labcontrol.domain.CourseMember;
import com.pnu.lab.control.labcontrol.repository.BaseSearchRepository;
import com.pnu.lab.control.labcontrol.repository.CourseMemberRepository;
import com.pnu.lab.control.labcontrol.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService extends AbstractSearchService<Course> {

    private final CourseRepository repository;
    private final CourseMemberRepository courseMemberRepository;

    public List<CourseMember> getCourseMembers(String id) {
        return courseMemberRepository.getCourseMembers(id);
    }

    public CourseMember addCourseMember(String id, CourseMember courseMember) {
        courseMember.setCourseId(id);
        return courseMemberRepository.save(courseMember);
    }

    public void deleteCourseMember(String id, String userId) {
        courseMemberRepository.deleteByCourseIdAndUserId(id, userId);
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
