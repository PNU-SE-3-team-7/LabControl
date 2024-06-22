package com.pnu.lab.control.labcontrol.repository;

import com.pnu.lab.control.labcontrol.api.dto.AssignmentPreviewDto;
import com.pnu.lab.control.labcontrol.domain.Assignment;
import com.pnu.lab.control.labcontrol.domain.QAssignment;
import com.pnu.lab.control.labcontrol.domain.QSubmission;
import com.pnu.lab.control.labcontrol.utils.QueryDslFactory;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.EntityPathBase;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssignmentRepository extends BaseSearchRepository<Assignment> {
    QAssignment qAssignment = QAssignment.assignment;

    default List<AssignmentPreviewDto> getAssignmentsByCourseId(String courseId, String userId) {
        QSubmission qSubmission = QSubmission.submission;
        return QueryDslFactory.getQueryFactory()
                .select(Projections.bean(AssignmentPreviewDto.class, qAssignment.id, qAssignment.type, qAssignment.dueDate, qSubmission.grade, qAssignment.maxGrade))
                .from(qAssignment)
                .innerJoin(qSubmission).on(qSubmission.assignmentId.eq(qAssignment.id))
                .where(qAssignment.courseId.eq(courseId), qSubmission.userId.eq(userId))
                .fetch();
    }

    List<Assignment> getAssignmentsByParentId(String parentId);

    @Override
    default EntityPathBase<Assignment> getQEntity() {
        return qAssignment;
    }
}
