package com.pnu.lab.control.labcontrol.service;

import com.pnu.lab.control.labcontrol.api.dto.AssignmentPreviewDto;
import com.pnu.lab.control.labcontrol.api.dto.AssignmentSearchRequest;
import com.pnu.lab.control.labcontrol.domain.Assignment;
import com.pnu.lab.control.labcontrol.exception.ValidationException;
import com.pnu.lab.control.labcontrol.repository.AssignmentRepository;
import com.pnu.lab.control.labcontrol.repository.BaseSearchRepository;
import com.pnu.lab.control.labcontrol.service.event.AssignmentDeleteEvent;
import com.pnu.lab.control.labcontrol.utils.UserUtils;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AssignmentService extends AbstractSearchService<Assignment> {

    private final AssignmentRepository repository;
    private final AssignmentCommentService commentService;
    private final ApplicationEventPublisher applicationEventPublisher;
    private final AssignmentAttachedContentService attachedContentService;

    public List<AssignmentPreviewDto> getList(AssignmentSearchRequest request) {
        if (StringUtils.isAllBlank(request.getAssignmentId(), request.getCourseId())
                || StringUtils.isNoneBlank(request.getAssignmentId(), request.getCourseId())) {
            throw new ValidationException("Either Assignment Id or Course Id should be filled");
        }

        return repository.getList(request, UserUtils.getUserId());
    }

    @Override
    public void delete(Assignment entity) {
        List<String> lowerAssignmentIds = repository.getLowerAssignmentIds(entity.getId());

        commentService.deleteByPrimaryObjectIds(lowerAssignmentIds);
        attachedContentService.deleteByAssignmentIds(lowerAssignmentIds);
        applicationEventPublisher.publishEvent(new AssignmentDeleteEvent(lowerAssignmentIds));
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
