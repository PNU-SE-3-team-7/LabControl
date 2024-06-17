package com.pnu.lab.control.labcontrol.api;

import com.pnu.lab.control.labcontrol.api.dto.SearchRequest;
import com.pnu.lab.control.labcontrol.domain.comment.AssignmentComment;
import com.pnu.lab.control.labcontrol.service.AssignmentCommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    public List<AssignmentComment> getList(@Valid @RequestBody SearchRequest searchRequest) {
        return service.getList(searchRequest);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}
