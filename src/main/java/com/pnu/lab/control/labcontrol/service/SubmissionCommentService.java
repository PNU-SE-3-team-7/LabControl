package com.pnu.lab.control.labcontrol.service;


import com.pnu.lab.control.labcontrol.domain.comment.SubmissionComment;
import com.pnu.lab.control.labcontrol.repository.BaseSearchRepository;
import com.pnu.lab.control.labcontrol.repository.SubmissionCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubmissionCommentService extends AbstractSearchService<SubmissionComment> {

    private final SubmissionCommentRepository repository;

    @Override
    public Class<SubmissionComment> getType() {
        return SubmissionComment.class;
    }

    @Override
    public BaseSearchRepository<SubmissionComment> getRepository() {
        return repository;
    }
}