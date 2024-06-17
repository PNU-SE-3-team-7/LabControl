package com.pnu.lab.control.labcontrol.domain.attached.content;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "assignment_attached_content")
public class AssignmentAttachedContent extends AttachedContentBase{
    @Column(name = "assignment_id")
    private String assignmentId;
}
