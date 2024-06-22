package com.pnu.lab.control.labcontrol.domain;

import com.pnu.lab.control.labcontrol.constant.AssignmentType;
import com.pnu.lab.control.labcontrol.constant.AutoType;
import com.pnu.lab.control.labcontrol.constant.GradeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "assignment")
public class Assignment extends BaseEntity {
    @NotBlank
    @Column(name = "course_id")
    private String courseId;
    @Column(name = "parent_id")
    private String parentId;
    @NotBlank
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private AssignmentType type;
    @NotNull
    @Column(name = "submission_enabled")
    private boolean submissionEnabled;
    @LastModifiedDate
    @Column(name = "updated_date")
    private LocalDateTime updatedDate;
    @CreatedDate
    @Column(name = "created_date")
    private LocalDateTime createdDate;
    @Column(name = "due_date")
    private LocalDateTime dueDate;
    @Column(name = "visibility_start")
    private LocalDateTime visibilityStart;
    @Column(name = "visibility_end")
    private LocalDateTime visibilityEnd;
    @NotNull
    @Column(name = "visibility")
    private boolean visibility;
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "grade_type")
    private GradeType gradeType;
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "auto_type")
    private AutoType autoType;
    @NotNull
    @Min(0)
    @Column(name = "max_grade")
    private int maxGrade;
    @NotNull
    @Min(0)
    @Column(name = "weight")
    private int weight;
    @Column(name = "threshold")
    private int threshold;
    @NotNull
    @Min(0)
    @Column(name = "sequence")
    private int sequence;
}
