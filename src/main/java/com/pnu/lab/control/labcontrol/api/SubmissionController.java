package com.pnu.lab.control.labcontrol.api;

import com.pnu.lab.control.labcontrol.api.validator.SubmissionValidator;
import com.pnu.lab.control.labcontrol.domain.Submission;
import com.pnu.lab.control.labcontrol.service.SubmissionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/submission")
public class SubmissionController {

    private final SubmissionService service;
    private final SubmissionValidator validator;

    @InitBinder("submission")
    public void init(WebDataBinder binder) {
        binder.addValidators(validator);
    }

    @PostMapping
    public Submission create(@Valid @RequestBody Submission submission) {
        return service.create(submission);
    }

    @PutMapping
    public Submission update(@Valid @RequestBody Submission submission) {
        return service.update(submission);
    }

    @GetMapping("/byAssignment/{assignmentId}")
    public Submission getByAssignmentId(@PathVariable String assignmentId) {
        return service.getByAssignmentId(assignmentId);
    }

    @GetMapping("/{id}")
    public Submission getById(@PathVariable String id) {
        return service.findOne(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}
