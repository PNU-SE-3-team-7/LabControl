package com.pnu.lab.control.labcontrol.domain;

import com.pnu.lab.control.labcontrol.constant.AccuracyGrade;
import com.pnu.lab.control.labcontrol.constant.CompletionGrade;
import com.pnu.lab.control.labcontrol.constant.SubmissionStatus;
import com.pnu.lab.control.labcontrol.domain.attached.content.SubmissionAttachedContent;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "submission")
public class Submission extends BaseEntity {
    @NotBlank
    @Column(name = "assignment_id")
    private String assignmentId;
    @NotBlank
    @Column(name = "user_id")
    private String userId;
    @Column(name = "parent_id")
    private String parentId;
    @Column(name = "turned_in_date")
    private LocalDateTime turnedInDate;
    @Enumerated(EnumType.STRING)
    @Column(name = "submission_status")
    private SubmissionStatus submissionStatus;
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
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "accuracy_grade")
    private AccuracyGrade accuracyGrade;
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "completion_grade")
    private CompletionGrade completionGrade;

    @Valid
    @NotEmpty
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "submission_id")
    private List<SubmissionAttachedContent> attachments;

    public boolean isChild() {
        return StringUtils.isNotBlank(parentId);
    }
}
