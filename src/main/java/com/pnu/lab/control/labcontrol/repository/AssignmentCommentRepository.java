package com.pnu.lab.control.labcontrol.repository;

import com.pnu.lab.control.labcontrol.domain.comment.AssignmentComment;
import com.pnu.lab.control.labcontrol.domain.comment.QAssignmentComment;
import com.querydsl.core.types.dsl.EntityPathBase;
import org.springframework.stereotype.Repository;

@Repository
public interface AssignmentCommentRepository extends BaseSearchRepository<AssignmentComment> {
    QAssignmentComment qAssignmentComment = QAssignmentComment.assignmentComment;

    @Override
    default EntityPathBase<AssignmentComment> getQEntity() {
        return qAssignmentComment;
    }
}
