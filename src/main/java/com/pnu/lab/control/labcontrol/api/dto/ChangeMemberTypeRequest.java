package com.pnu.lab.control.labcontrol.api.dto;

import com.pnu.lab.control.labcontrol.constant.MemberType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangeMemberTypeRequest {

    @NotBlank
    private String userId;
    @NotNull
    private MemberType memberType;

}
