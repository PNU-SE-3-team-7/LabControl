package com.pnu.lab.control.labcontrol.api;

import com.pnu.lab.control.labcontrol.api.dto.CommentRequest;
import com.pnu.lab.control.labcontrol.domain.comment.SubmissionComment;
import com.pnu.lab.control.labcontrol.service.SubmissionCommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public List<SubmissionComment> getList(@Valid @RequestBody CommentRequest request) {
        return service.getCommentList(request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}
