package com.pnu.lab.control.labcontrol.repository;

import com.pnu.lab.control.labcontrol.domain.QAssignment;
import com.pnu.lab.control.labcontrol.domain.QSubmission;
import com.pnu.lab.control.labcontrol.domain.Submission;
import com.pnu.lab.control.labcontrol.utils.QueryDslFactory;
import com.querydsl.core.types.dsl.EntityPathBase;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface SubmissionRepository extends BaseSearchRepository<Submission> {
    QSubmission qSubmission = QSubmission.submission;

    Submission getByAssignmentId(String assignmentId);

    default List<Submission> getSubmissionsByParentId(String parentId) {
        return QueryDslFactory.getQueryFactory()
                .selectFrom(qSubmission)
                .where(qSubmission.parentId.eq(parentId), qSubmission.grade.gt(0))
                .fetch();
    }

    default List<String> getSubmissionIdsByAssignmentIds(Collection<String> assignmentIds) {
        return QueryDslFactory.getQueryFactory()
                .select(qSubmission.id)
                .where(qSubmission.assignmentId.in(assignmentIds))
                .fetch();
    }

    default List<String> getSubmissionIdsByCourseIdAndUserId(String courseId, String userId) {
        QAssignment qAssignment = QAssignment.assignment;
        return QueryDslFactory.getQueryFactory()
                .select(qSubmission.id)
                .innerJoin(qAssignment).on(qAssignment.id.eq(qSubmission.assignmentId))
                .where(qSubmission.userId.eq(userId), qAssignment.courseId.eq(courseId))
                .fetch();
    }

    @Override
    default EntityPathBase<Submission> getQEntity() {
        return qSubmission;
    }
}
