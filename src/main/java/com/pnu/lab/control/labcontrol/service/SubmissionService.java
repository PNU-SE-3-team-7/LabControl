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

    public Submission getByAssignmentId(String assignmentId) {
        return repository.getByAssignmentId(assignmentId);
    }

    @Override
    public void delete(Submission entity) {
        commentService.deleteByPrimaryObjectId(entity.getId());
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
