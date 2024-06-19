package com.pnu.lab.control.labcontrol.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "course")
public class Course extends BaseEntity {
    @NotBlank
    @Column(name = "name")
    private String name;
    @Column(name = "summary")
    private String summary;
    @NotBlank
    @Column(name = "owner_id")
    private String ownerId;
}
