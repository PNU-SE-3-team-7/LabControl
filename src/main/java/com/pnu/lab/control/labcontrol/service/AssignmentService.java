package com.pnu.lab.control.labcontrol.service;

import com.pnu.lab.control.labcontrol.api.dto.AssignmentPreviewDto;
import com.pnu.lab.control.labcontrol.domain.Assignment;
import com.pnu.lab.control.labcontrol.repository.AssignmentRepository;
import com.pnu.lab.control.labcontrol.repository.BaseSearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AssignmentService extends AbstractSearchService<Assignment> {

    private final AssignmentRepository repository;
    private final AssignmentCommentService commentService;
    private final AssignmentAttachedContentService attachedContentService;

    public List<AssignmentPreviewDto> getListByCourseId(String courseId, String userId) {
        return repository.getAssignmentsByCourseId(courseId, userId);
    }

    public List<Assignment> getListByAssignmentId(String assignmentId, String userId) {
        return repository.getAssignmentsByParentId(assignmentId);
    }

    @Override
    public void delete(Assignment entity) {
        commentService.deleteByPrimaryObjectId(entity.getId());
        attachedContentService.deleteByAssignmentId(entity.getId());
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
