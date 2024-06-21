package com.pnu.lab.control.labcontrol.domain;

import com.pnu.lab.control.labcontrol.constant.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User extends BaseEntity {
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String last_name;
    @Email
    @Column(name = "email")
    private String email;
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;
}
