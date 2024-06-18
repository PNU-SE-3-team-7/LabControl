package com.pnu.lab.control.labcontrol.api;

import com.pnu.lab.control.labcontrol.api.dto.CommentRequest;
import com.pnu.lab.control.labcontrol.domain.comment.AssignmentComment;
import com.pnu.lab.control.labcontrol.service.AssignmentCommentService;
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
@RequestMapping("/assignment/comment")
public class AssignmentCommentController {

    private final AssignmentCommentService service;

    @PostMapping
    public AssignmentComment create(@Valid @RequestBody AssignmentComment assignmentComment) {
        return service.create(assignmentComment);
    }

    @PutMapping
    public AssignmentComment update(@Valid @RequestBody AssignmentComment assignmentComment) {
        return service.update(assignmentComment);
    }

    @PostMapping("/list")
    public List<AssignmentComment> getList(@Valid @RequestBody CommentRequest request) {
        return service.getCommentList(request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}
