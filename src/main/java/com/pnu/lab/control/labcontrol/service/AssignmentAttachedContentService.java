package com.pnu.lab.control.labcontrol.service;

import com.pnu.lab.control.labcontrol.domain.attached.content.AssignmentAttachedContent;
import com.pnu.lab.control.labcontrol.repository.AssignmentAttachedContentRepository;
import com.pnu.lab.control.labcontrol.repository.BaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AssignmentAttachedContentService extends AbstractBaseService<AssignmentAttachedContent> {

    private final AssignmentAttachedContentRepository repository;

    public List<AssignmentAttachedContent> getAttachedContentList(String assignmentId) {
        return repository.getAttachedContentList(assignmentId);
    }

    public void deleteByAssignmentIds(Collection<String> assignmentIds) {
        repository.deleteByAssignmentIds(assignmentIds);
    }

    @Override
    public Class<AssignmentAttachedContent> getType() {
        return AssignmentAttachedContent.class;
    }

    @Override
    public BaseRepository<AssignmentAttachedContent> getRepository() {
        return repository;
    }
}