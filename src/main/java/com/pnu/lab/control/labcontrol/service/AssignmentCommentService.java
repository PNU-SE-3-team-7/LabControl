package com.pnu.lab.control.labcontrol.service;

import com.pnu.lab.control.labcontrol.domain.comment.AssignmentComment;
import com.pnu.lab.control.labcontrol.repository.AssignmentCommentRepository;
import com.pnu.lab.control.labcontrol.repository.BaseCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AssignmentCommentService extends AbstractCommentService<AssignmentComment> {

    private final AssignmentCommentRepository repository;

    @Override
    public void delete(AssignmentComment comment) {
        comment.setDeleted(true);
        update(comment);
    }

    @Override
    public Class<AssignmentComment> getType() {
        return AssignmentComment.class;
    }

    @Override
    public BaseCommentRepository<AssignmentComment> getRepository() {
        return repository;
    }
}