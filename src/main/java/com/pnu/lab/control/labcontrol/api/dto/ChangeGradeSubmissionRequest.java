package com.pnu.lab.control.labcontrol.api.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangeGradeSubmissionRequest {

    @NotBlank
    private String submissionId;
    @NotNull
    @Min(0)
    private Integer grade;

}
