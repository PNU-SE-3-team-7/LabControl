package com.pnu.lab.control.labcontrol.api;

import com.pnu.lab.control.labcontrol.domain.attached.content.AssignmentAttachedContent;
import com.pnu.lab.control.labcontrol.service.AssignmentAttachedContentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/assignment/attachedContent")
public class AssignmentAttachedContentController {

    private final AssignmentAttachedContentService service;

    @PostMapping
    public AssignmentAttachedContent create(@Valid @RequestBody AssignmentAttachedContent assignmentAttachedContent) {
        return service.create(assignmentAttachedContent);
    }

    @GetMapping("/list")
    public List<AssignmentAttachedContent> getList(@RequestParam String assignmentId) {
        return service.getAttachedContentList(assignmentId);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}
