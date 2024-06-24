package com.pnu.lab.control.labcontrol.domain;

import com.pnu.lab.control.labcontrol.constant.MemberType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "course_member")
@IdClass(CourseMember.IdClass.class)
public class CourseMember {
    @NotBlank
    @Id
    @Column(name = "user_id")
    private String userId;
    @Id
    @Column(name = "course_id")
    private String courseId;
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "member_type")
    private MemberType memberType;

    static class IdClass implements Serializable {
        private String userId;
        private String courseId;
    }
}
