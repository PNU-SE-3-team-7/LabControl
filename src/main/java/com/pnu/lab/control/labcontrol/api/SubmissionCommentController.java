package com.pnu.lab.control.labcontrol.api;

import com.pnu.lab.control.labcontrol.api.dto.SearchRequest;
import com.pnu.lab.control.labcontrol.domain.comment.SubmissionComment;
import com.pnu.lab.control.labcontrol.service.SubmissionCommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/submission/comment")
public class SubmissionCommentController {

    private final SubmissionCommentService service;

    @PostMapping
    public SubmissionComment create(@Valid @RequestBody SubmissionComment submissionComment) {
        return service.create(submissionComment);
    }

    @PutMapping
    public SubmissionComment update(@Valid @RequestBody SubmissionComment submissionComment) {
        return service.update(submissionComment);
    }

    @PostMapping("/list")
    public List<SubmissionComment> getList(@Valid @RequestBody SearchRequest searchRequest) {
        return service.getList(searchRequest);
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}
