package com.pnu.lab.control.labcontrol.service.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AssignmentDeleteEvent {

    private List<String> lowerAssignmentIds;

}
