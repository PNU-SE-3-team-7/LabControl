package com.pnu.lab.control.labcontrol.service;

import com.pnu.lab.control.labcontrol.domain.Submission;
import com.pnu.lab.control.labcontrol.repository.BaseSearchRepository;
import com.pnu.lab.control.labcontrol.repository.SubmissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubmissionService extends AbstractSearchService<Submission> {

    private final SubmissionRepository repository;
    private final SubmissionCommentService commentService;
    private final SubmissionAttachedContentService attachedContentService;

    @Override
    public void delete(Submission entity) {
        commentService.deleteByPrimaryObjectId(entity.getId());
        attachedContentService.deleteByPrimaryObjectId(entity.getId());
        super.delete(entity);
    }

    @Override
    public Class<Submission> getType() {
        return Submission.class;
    }

    @Override
    public BaseSearchRepository<Submission> getRepository() {
        return repository;
    }
}
