package com.pnu.lab.control.labcontrol.repository;


import com.pnu.lab.control.labcontrol.domain.attached.content.QSubmissionAttachedContent;
import com.pnu.lab.control.labcontrol.domain.attached.content.SubmissionAttachedContent;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.stereotype.Repository;

@Repository
public interface SubmissionAttachedContentRepository extends BaseAttachedContentRepository<SubmissionAttachedContent> {
    QSubmissionAttachedContent qSubmissionAttachedContent = QSubmissionAttachedContent.submissionAttachedContent;

    @Override
    default EntityPathBase<SubmissionAttachedContent> getQEntity() {
        return qSubmissionAttachedContent;
    }

    @Override
    default StringPath getPrimaryObjectIdPath() {
        return qSubmissionAttachedContent.submissionId;
    }
}
