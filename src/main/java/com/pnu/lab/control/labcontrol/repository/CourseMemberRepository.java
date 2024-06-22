package com.pnu.lab.control.labcontrol.repository;

import com.pnu.lab.control.labcontrol.api.dto.CourseUserPreviewDto;
import com.pnu.lab.control.labcontrol.domain.CourseMember;
import com.pnu.lab.control.labcontrol.domain.QCourseMember;
import com.pnu.lab.control.labcontrol.domain.QUser;
import com.pnu.lab.control.labcontrol.utils.QueryDslFactory;
import com.querydsl.core.types.Projections;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseMemberRepository extends JpaRepository<CourseMember, String> {

    QCourseMember qCourseMember = QCourseMember.courseMember;

    void deleteByCourseId(String courseId);

    void deleteByCourseIdAndUserId(String courseId, String userId);

    Optional<CourseMember> findByCourseIdAndUserId(String courseId, String userId);

    default List<CourseUserPreviewDto> getCourseMembers(String courseId) {
        QUser qUser = QUser.user;
        return QueryDslFactory.getQueryFactory()
                .select(Projections.bean(CourseUserPreviewDto.class, qUser.id, qUser.firstName, qUser.lastName, qUser.email, qCourseMember.memberType))
                .from(qCourseMember)
                .innerJoin(qUser).on(qUser.id.eq(qCourseMember.userId))
                .where(qCourseMember.courseId.eq(courseId))
                .fetch();
    }
}
