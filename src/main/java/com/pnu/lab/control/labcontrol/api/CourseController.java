package com.pnu.lab.control.labcontrol.api;

import com.pnu.lab.control.labcontrol.api.dto.SearchRequest;
import com.pnu.lab.control.labcontrol.domain.Course;
import com.pnu.lab.control.labcontrol.domain.CourseMember;
import com.pnu.lab.control.labcontrol.service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(("/course"))
public class CourseController {

    private final CourseService courseService;

    @PostMapping
    public Course create(@Valid @RequestBody Course course) {
        return courseService.create(course);
    }

    @PutMapping
    public Course update(@Valid @RequestBody Course course) {
        return courseService.update(course);
    }

    @PostMapping("/list")
    public List<Course> getList(@Valid @RequestBody SearchRequest searchRequest) {
        return courseService.getList(searchRequest);
    }

    @GetMapping("/{id}/member/list")
    public List<CourseMember> getCourseMembers(@PathVariable String id) {
        return courseService.getCourseMembers(id);
    }

    @PostMapping("/{id}/member")
    public CourseMember addCourseMember(@PathVariable String id, @Valid @RequestBody CourseMember courseMember) {
        return courseService.addCourseMember(id, courseMember);
    }

    @DeleteMapping("/{id}/member/{userId}")
    public void deleteCourseMember(@PathVariable String id, @PathVariable String userId) {
        courseService.deleteCourseMember(id, userId);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        courseService.delete(id);
    }
}
