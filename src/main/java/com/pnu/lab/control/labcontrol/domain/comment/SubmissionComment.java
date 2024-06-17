package com.pnu.lab.control.labcontrol.domain.comment;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "submission_comment")
public class SubmissionComment extends CommentBase {
    @Column(name = "submission_id")
    private String submissionId;
}
