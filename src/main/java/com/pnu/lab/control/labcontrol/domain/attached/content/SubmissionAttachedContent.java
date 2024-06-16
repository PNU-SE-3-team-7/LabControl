package com.pnu.lab.control.labcontrol.domain.attached.content;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "submission_attached_content")
public class SubmissionAttachedContent extends AttachedContentBase{
    @Column(name = "submission_id")
    private String submissionId;
}
