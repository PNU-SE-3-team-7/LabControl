package com.pnu.lab.control.labcontrol.domain.comment;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "assignment_comment")
public class AssignmentComment extends CommentBase {
    @Column(name = "assignment_id")
    private String assignmentId;
    @Column(name = "deleted")
    private boolean deleted;
}
