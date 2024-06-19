package com.pnu.lab.control.labcontrol.service;

import com.pnu.lab.control.labcontrol.domain.attached.content.AssignmentAttachedContent;
import com.pnu.lab.control.labcontrol.repository.AssignmentAttachedContentRepository;
import com.pnu.lab.control.labcontrol.repository.BaseAttachedContentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AssignmentAttachedContentService extends AbstractAttachedContentService<AssignmentAttachedContent> {

    private final AssignmentAttachedContentRepository repository;

    @Override
    public Class<AssignmentAttachedContent> getType() {
        return AssignmentAttachedContent.class;
    }

    @Override
    public BaseAttachedContentRepository<AssignmentAttachedContent> getRepository() {
        return repository;
    }
}