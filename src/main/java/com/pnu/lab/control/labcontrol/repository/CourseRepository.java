package com.pnu.lab.control.labcontrol.repository;

import com.pnu.lab.control.labcontrol.domain.Course;
import com.pnu.lab.control.labcontrol.domain.QCourse;
import com.querydsl.core.types.dsl.EntityPathBase;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends BaseSearchRepository<Course> {

    QCourse qCourse = QCourse.course;

    @Override
    default EntityPathBase<Course> getQEntity() {
        return qCourse;
    }
}
