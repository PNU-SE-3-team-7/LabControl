package com.pnu.lab.control.labcontrol.api.dto;

import com.pnu.lab.control.labcontrol.constant.AssignmentType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class AssignmentPreviewDto {

    private String id;
    private AssignmentType type;
    private LocalDateTime dueDate;
    private int grade;
    private int maxGrade;

}
