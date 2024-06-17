package com.pnu.lab.control.labcontrol.repository;

import com.pnu.lab.control.labcontrol.domain.comment.QSubmissionComment;
import com.pnu.lab.control.labcontrol.domain.comment.SubmissionComment;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.stereotype.Repository;

@Repository
public interface SubmissionCommentRepository extends BaseCommentRepository<SubmissionComment> {
    QSubmissionComment qSubmissionComment = QSubmissionComment.submissionComment;

    @Override
    default EntityPathBase<SubmissionComment> getQEntity() {
        return qSubmissionComment;
    }

    @Override
    default StringPath getPrimaryObjectIdPath() {
        return qSubmissionComment.submissionId;
    }
}
