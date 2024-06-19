package com.pnu.lab.control.labcontrol.repository;

import com.pnu.lab.control.labcontrol.domain.CourseMember;
import com.pnu.lab.control.labcontrol.domain.QCourseMember;
import com.pnu.lab.control.labcontrol.utils.QueryDslFactory;
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

    default List<CourseMember> getCourseMembers(String courseId) {
        return QueryDslFactory.getQueryFactory()
                .selectFrom(qCourseMember)
                .where(qCourseMember.courseId.eq(courseId))
                .fetch();
    }
}
