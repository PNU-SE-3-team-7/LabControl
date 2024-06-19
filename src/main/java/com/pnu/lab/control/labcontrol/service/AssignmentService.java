package com.pnu.lab.control.labcontrol.service;

import com.pnu.lab.control.labcontrol.domain.Assignment;
import com.pnu.lab.control.labcontrol.repository.AssignmentRepository;
import com.pnu.lab.control.labcontrol.repository.BaseSearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AssignmentService extends AbstractSearchService<Assignment> {

    private final AssignmentRepository repository;
    private final AssignmentCommentService commentService;
    private final AssignmentAttachedContentService attachedContentService;

    @Override
    public void delete(Assignment entity) {
        commentService.deleteByPrimaryObjectId(entity.getId());
        attachedContentService.deleteByPrimaryObjectId(entity.getId());
        super.delete(entity);
    }

    @Override
    public Class<Assignment> getType() {
        return Assignment.class;
    }

    @Override
    public BaseSearchRepository<Assignment> getRepository() {
        return repository;
    }
}
