package com.pnu.lab.control.labcontrol.repository;


import com.pnu.lab.control.labcontrol.domain.attached.content.AssignmentAttachedContent;
import com.pnu.lab.control.labcontrol.domain.attached.content.QAssignmentAttachedContent;
import com.pnu.lab.control.labcontrol.utils.QueryDslFactory;
import com.querydsl.core.types.dsl.EntityPathBase;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssignmentAttachedContentRepository extends BaseSearchRepository<AssignmentAttachedContent> {
    QAssignmentAttachedContent qAssignmentAttachedContent = QAssignmentAttachedContent.assignmentAttachedContent;

    default List<AssignmentAttachedContent> getAttachedContentList(String assignmentId) {
        return QueryDslFactory.getQueryFactory()
                .selectFrom(qAssignmentAttachedContent)
                .where(qAssignmentAttachedContent.assignmentId.eq(assignmentId))
                .fetch();
    }

    default void deleteByAssignmentId(String primaryObjectId) {
        QueryDslFactory.getQueryFactory()
                .delete(qAssignmentAttachedContent)
                .where(qAssignmentAttachedContent.assignmentId.eq(primaryObjectId))
                .execute();
    }

    @Override
    default EntityPathBase<AssignmentAttachedContent> getQEntity() {
        return qAssignmentAttachedContent;
    }
}
