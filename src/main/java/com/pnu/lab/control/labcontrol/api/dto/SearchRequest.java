package com.pnu.lab.control.labcontrol.api.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchRequest {

    @Max(1000000)
    private long limit;
    @Min(0)
    private long offset;

}
