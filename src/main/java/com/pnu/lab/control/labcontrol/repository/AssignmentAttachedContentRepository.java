package com.pnu.lab.control.labcontrol.repository;


import com.pnu.lab.control.labcontrol.domain.attached.content.AssignmentAttachedContent;
import com.pnu.lab.control.labcontrol.domain.attached.content.QAssignmentAttachedContent;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.stereotype.Repository;

@Repository
public interface AssignmentAttachedContentRepository extends BaseAttachedContentRepository<AssignmentAttachedContent> {
    QAssignmentAttachedContent qAssignmentAttachedContent = QAssignmentAttachedContent.assignmentAttachedContent;

    @Override
    default EntityPathBase<AssignmentAttachedContent> getQEntity() {
        return qAssignmentAttachedContent;
    }

    @Override
    default StringPath getPrimaryObjectIdPath() {
        return qAssignmentAttachedContent.assignmentId;
    }
}
