package com.pnu.lab.control.labcontrol.domain.attached.content;

import com.pnu.lab.control.labcontrol.domain.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class AttachedContentBase extends BaseEntity {
    @Column(name = "file_name")
    private String fileName;
    @Column(name = "file_uri")
    private String fileUri;
}
