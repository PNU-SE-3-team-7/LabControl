package com.pnu.lab.control.labcontrol.repository;

import com.pnu.lab.control.labcontrol.api.dto.CoursePreviewDto;
import com.pnu.lab.control.labcontrol.api.dto.UserCourseListRequest;
import com.pnu.lab.control.labcontrol.domain.Course;
import com.pnu.lab.control.labcontrol.domain.QCourse;
import com.pnu.lab.control.labcontrol.domain.QCourseMember;
import com.pnu.lab.control.labcontrol.utils.QueryDslFactory;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.EntityPathBase;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends BaseSearchRepository<Course> {

    QCourse qCourse = QCourse.course;

    default List<CoursePreviewDto> getUserCourseList(UserCourseListRequest request) {
        QCourseMember qCourseMember = QCourseMember.courseMember;
        return QueryDslFactory.getQueryFactory()
                .select(Projections.bean(CoursePreviewDto.class, qCourse.id, qCourse.name, qCourse.summary, qCourse.ownerId))
                .from(qCourse)
                .innerJoin(qCourseMember).on(qCourseMember.courseId.eq(qCourse.id))
                .where(qCourseMember.userId.eq(request.getUserId()), qCourseMember.memberType.eq(request.getMemberType()))
                .limit(request.getLimit())
                .offset(request.getOffset())
                .fetch();
    }

    @Override
    default EntityPathBase<Course> getQEntity() {
        return qCourse;
    }
}
