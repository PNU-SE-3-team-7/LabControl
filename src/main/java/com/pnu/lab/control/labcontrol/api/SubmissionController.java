package com.pnu.lab.control.labcontrol.api;

import com.pnu.lab.control.labcontrol.api.dto.SearchRequest;
import com.pnu.lab.control.labcontrol.domain.Submission;
import com.pnu.lab.control.labcontrol.service.SubmissionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/submission")
public class SubmissionController {

    private final SubmissionService service;

    @PostMapping
    public Submission create(@Valid @RequestBody Submission submission) {
        return service.create(submission);
    }

    @PutMapping
    public Submission update(@Valid @RequestBody Submission submission) {
        return service.update(submission);
    }

    @PostMapping("/list")
    public List<Submission> getList(@Valid @RequestBody SearchRequest searchRequest) {
        return service.getList(searchRequest);

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
