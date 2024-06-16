package com.pnu.lab.control.labcontrol.domain;

import com.pnu.lab.control.labcontrol.constant.AccuracyGrade;
import com.pnu.lab.control.labcontrol.constant.CompletionGrade;
import com.pnu.lab.control.labcontrol.constant.SubmissionStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "submission")
public class Submission extends BaseEntity {
    @Column(name = "assignment_id")
    private String assignmentId;
    @Column(name = "user_id")
    private String userId;
    @Column(name = "parent_id")
    private String parentId;
    @Column(name = "turned_in_date")
    private LocalDateTime turnedInDate;
    @Enumerated(EnumType.STRING)
    @Column(name = "turned_in")
    private SubmissionStatus turnedIn;
    @Column(name = "grade")
    private int grade;
    @Column(name = "auto_grade")
    private int autoGrade;
    @CreatedDate
    @Column(name = "created_date")
    private LocalDateTime createdDate;
    @LastModifiedDate
    @Column(name = "updated_date")
    private LocalDateTime updatedDate;
    @Enumerated(EnumType.STRING)
    @Column(name = "accuracy_grade")
    private AccuracyGrade accuracyGrade;
    @Enumerated(EnumType.STRING)
    @Column(name = "completion_grade")
    private CompletionGrade completionGrade;
}
