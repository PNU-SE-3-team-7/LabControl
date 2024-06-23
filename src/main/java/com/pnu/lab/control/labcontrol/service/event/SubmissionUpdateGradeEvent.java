package com.pnu.lab.control.labcontrol.service.event;

import com.pnu.lab.control.labcontrol.domain.Submission;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubmissionUpdateGradeEvent {

    private int oldGrade;
    private Submission submission;

}
