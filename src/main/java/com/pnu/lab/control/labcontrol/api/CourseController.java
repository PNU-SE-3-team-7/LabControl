package com.pnu.lab.control.labcontrol.api;

import com.pnu.lab.control.labcontrol.api.dto.ChangeMemberTypeRequest;
import com.pnu.lab.control.labcontrol.api.dto.CoursePreviewDto;
import com.pnu.lab.control.labcontrol.api.dto.CourseUserPreviewDto;
import com.pnu.lab.control.labcontrol.api.dto.UserCourseListRequest;
import com.pnu.lab.control.labcontrol.domain.Course;
import com.pnu.lab.control.labcontrol.domain.CourseMember;
import com.pnu.lab.control.labcontrol.service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(("/course"))
public class CourseController {

    private final CourseService courseService;

    @GetMapping("/{id}/")
    public Course findOne(@PathVariable String id) {
        return courseService.findOne(id);
    }

    @PostMapping
    public Course create(@Valid @RequestBody Course course) {
        return courseService.create(course);
    }

    @PutMapping
    public Course update(@Valid @RequestBody Course course) {
        return courseService.update(course);
    }

    @PostMapping("/list")
    public List<CoursePreviewDto> getUserCourseList(@Valid @RequestBody UserCourseListRequest request) {
        return courseService.getUserCourseList(request);
    }

    @GetMapping("/{id}/member/list")
    public List<CourseUserPreviewDto> getCourseMembers(@PathVariable String id) {
        return courseService.getCourseMembers(id);
    }

    @GetMapping("/list/byOwner")
    public List<CoursePreviewDto> getCourseListByOwner(@RequestParam String ownerId) {
        return courseService.getCourseListByOwner(ownerId);
    }

    @PostMapping("/{id}/member")
    public CourseMember addCourseMember(@PathVariable String id, @Valid @RequestBody CourseMember courseMember) {
        return courseService.addCourseMember(id, courseMember);
    }

    @PatchMapping("/{id}/member/type")
    public void changeMemberType(@PathVariable String id, @Valid @RequestBody ChangeMemberTypeRequest request) {
        courseService.changeMemberType(id, request);
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
