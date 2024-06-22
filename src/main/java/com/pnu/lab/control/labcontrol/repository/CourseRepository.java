package com.pnu.lab.control.labcontrol.repository;

import com.pnu.lab.control.labcontrol.api.dto.CoursePreviewDto;
import com.pnu.lab.control.labcontrol.api.dto.UserCourseListRequest;
import com.pnu.lab.control.labcontrol.api.dto.UserPreviewDto;
import com.pnu.lab.control.labcontrol.domain.Course;
import com.pnu.lab.control.labcontrol.domain.QCourse;
import com.pnu.lab.control.labcontrol.domain.QCourseMember;
import com.pnu.lab.control.labcontrol.domain.QUser;
import com.pnu.lab.control.labcontrol.utils.QueryDslFactory;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.QBean;
import com.querydsl.core.types.dsl.EntityPathBase;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends BaseSearchRepository<Course> {

    QCourse qCourse = QCourse.course;
    QUser qUser = QUser.user;


    default List<CoursePreviewDto> getUserCourseList(UserCourseListRequest request) {
        QCourseMember qCourseMember = QCourseMember.courseMember;
        return QueryDslFactory.getQueryFactory()
                .select(getCoursePreviewDtoProjectionBean())
                .from(qCourse)
                .innerJoin(qUser).on(qUser.id.eq(qCourse.ownerId))
                .innerJoin(qCourseMember).on(qCourseMember.courseId.eq(qCourse.id))
                .where(qCourseMember.userId.eq(request.getUserId()), qCourseMember.memberType.eq(request.getMemberType()))
                .limit(request.getLimit())
                .offset(request.getOffset())
                .fetch();
    }

    default List<CoursePreviewDto> getCourseListByOwner(String ownerId) {
        return QueryDslFactory.getQueryFactory()
                .select(getCoursePreviewDtoProjectionBean())
                .from(qCourse)
                .innerJoin(qUser).on(qUser.id.eq(qCourse.ownerId))
                .where(qCourse.ownerId.eq(ownerId))
                .fetch();
    }

    default QBean<CoursePreviewDto> getCoursePreviewDtoProjectionBean() {
        return Projections.bean(CoursePreviewDto.class, qCourse.id, qCourse.name, qCourse.summary,
                Projections.bean(UserPreviewDto.class, qUser.id, qUser.firstName, qUser.lastName, qUser.email));
    }

    @Override
    default EntityPathBase<Course> getQEntity() {
        return qCourse;
    }
}
