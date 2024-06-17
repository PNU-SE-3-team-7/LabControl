package com.pnu.lab.control.labcontrol.service;

import com.pnu.lab.control.labcontrol.domain.comment.AssignmentComment;
import com.pnu.lab.control.labcontrol.repository.AssignmentCommentRepository;
import com.pnu.lab.control.labcontrol.repository.BaseSearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AssignmentCommentService extends AbstractSearchService<AssignmentComment> {

    private final AssignmentCommentRepository repository;

    @Override
    public Class<AssignmentComment> getType() {
        return AssignmentComment.class;
    }

    @Override
    public BaseSearchRepository<AssignmentComment> getRepository() {
        return repository;
    }
}