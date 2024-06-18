package com.pnu.lab.control.labcontrol.api;

import com.pnu.lab.control.labcontrol.api.dto.AttachedContentRequest;
import com.pnu.lab.control.labcontrol.domain.attached.content.SubmissionAttachedContent;
import com.pnu.lab.control.labcontrol.service.SubmissionAttachedContentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/submission/attachedContent")
public class SubmissionAttachedContentController {

    private final SubmissionAttachedContentService service;

    @PostMapping
    public SubmissionAttachedContent create(@Valid @RequestBody SubmissionAttachedContent submissionAttachedContent) {
        return service.create(submissionAttachedContent);
    }

    @PostMapping("/list")
    public List<SubmissionAttachedContent> getList(@Valid @RequestBody AttachedContentRequest request) {
        return service.getAttachedContentList(request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}
