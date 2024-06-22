package com.pnu.lab.control.labcontrol.repository;

import com.pnu.lab.control.labcontrol.domain.QSubmission;
import com.pnu.lab.control.labcontrol.domain.Submission;
import com.querydsl.core.types.dsl.EntityPathBase;
import org.springframework.stereotype.Repository;

@Repository
public interface SubmissionRepository extends BaseSearchRepository<Submission> {
    QSubmission qSubmission = QSubmission.submission;

    Submission getByAssignmentId(String assignmentId);

    @Override
    default EntityPathBase<Submission> getQEntity() {
        return qSubmission;
    }
}
