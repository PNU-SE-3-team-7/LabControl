package com.pnu.lab.control.labcontrol.repository;

import com.pnu.lab.control.labcontrol.domain.CourseMember;
import com.pnu.lab.control.labcontrol.domain.QCourseMember;
import com.pnu.lab.control.labcontrol.utils.QueryDslFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseMemberRepository extends JpaRepository<CourseMember, String> {

    QCourseMember qCourseMember = QCourseMember.courseMember;

    void deleteByCourseId(String courseId);

    void deleteByCourseIdAndUserId(String courseId, String userId);

    default List<CourseMember> getCourseMembers(String courseId) {
        return QueryDslFactory.getQueryFactory()
                .selectFrom(qCourseMember)
                .where(qCourseMember.courseId.eq(courseId))
                .orderBy(qCourseMember.memberType.desc()) //TODO 6/16/24: If pagination will be added, check this fetching. Because we may need to display EDUCATOR and OWNER firstly
                .fetch();
    }
}
