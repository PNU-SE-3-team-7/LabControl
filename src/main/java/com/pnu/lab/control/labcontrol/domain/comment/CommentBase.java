package com.pnu.lab.control.labcontrol.domain.comment;

import com.pnu.lab.control.labcontrol.domain.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public class CommentBase extends BaseEntity {
    @Column(name = "sender_id")
    private String senderId;
    @CreatedDate
    @Column(name = "created_date")
    private LocalDateTime createdDate;
    @Column(name = "message")
    private String message;
}

