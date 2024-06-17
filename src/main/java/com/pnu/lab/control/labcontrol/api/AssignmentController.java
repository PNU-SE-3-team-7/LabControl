package com.pnu.lab.control.labcontrol.api;

import com.pnu.lab.control.labcontrol.api.dto.SearchRequest;
import com.pnu.lab.control.labcontrol.domain.Assignment;
import com.pnu.lab.control.labcontrol.service.AssignmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController("/assignment")
public class AssignmentController {
    private final AssignmentService service;

    @PostMapping
    public Assignment create(@Valid @RequestBody Assignment assignment) {
        return service.create(assignment);
    }

    /*@PutMapping
    public Assignment update(@Valid @RequestBody Assignment assignment) {
        return service.update(assignment);
    }

    @PostMapping("/list")
    public List<Assignment> getList(@Valid @RequestBody SearchRequest searchRequest) {
        return service.getList(searchRequest);

    }
    @GetMapping("/{id}")
    public Assignment getById(@PathVariable String id) {
        return service.findOne(id);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }*/
}
