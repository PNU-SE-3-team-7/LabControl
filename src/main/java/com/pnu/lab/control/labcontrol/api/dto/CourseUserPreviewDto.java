package com.pnu.lab.control.labcontrol.api.dto;

import com.pnu.lab.control.labcontrol.constant.MemberType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseUserPreviewDto extends UserPreviewDto {

    private MemberType memberType;

}
