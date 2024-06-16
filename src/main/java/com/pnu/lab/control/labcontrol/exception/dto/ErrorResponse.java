package com.pnu.lab.control.labcontrol.exception.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class ErrorResponse {

    private String message;
    private List<RejectedFieldsDto> rejectedFields;

    public ErrorResponse(String message) {
        this.message = message;
    }

    public List<RejectedFieldsDto> getRejectedFields() {
        if (Objects.isNull(rejectedFields)) {
            rejectedFields = new ArrayList<>();
        }
        return rejectedFields;
    }
}
