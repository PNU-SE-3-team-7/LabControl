package com.pnu.lab.control.labcontrol.service;

import com.pnu.lab.control.labcontrol.api.dto.CommentRequest;
import com.pnu.lab.control.labcontrol.domain.comment.CommentBase;
import com.pnu.lab.control.labcontrol.repository.BaseCommentRepository;

import java.util.List;

public abstract class AbstractCommentService<T extends CommentBase> extends AbstractBaseService<T> {

    public List<T> getCommentList(CommentRequest request) {
        return getRepository().getCommentList(request.getPrimaryObjectId());
    }

    public abstract BaseCommentRepository<T> getRepository();

}
