package com.pnu.lab.control.labcontrol.service.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserLeaveCourseEvent {

    private String courseId;
    private String userId;

}
