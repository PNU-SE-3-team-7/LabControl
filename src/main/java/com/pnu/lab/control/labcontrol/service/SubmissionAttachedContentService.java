package com.pnu.lab.control.labcontrol.service;

import com.pnu.lab.control.labcontrol.domain.attached.content.SubmissionAttachedContent;
import com.pnu.lab.control.labcontrol.repository.BaseAttachedContentRepository;
import com.pnu.lab.control.labcontrol.repository.SubmissionAttachedContentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubmissionAttachedContentService extends AbstractAttachedContentService<SubmissionAttachedContent> {

    private final SubmissionAttachedContentRepository repository;

    @Override
    public Class<SubmissionAttachedContent> getType() {
        return SubmissionAttachedContent.class;
    }

    @Override
    public BaseAttachedContentRepository<SubmissionAttachedContent> getRepository() {
        return repository;
    }
}