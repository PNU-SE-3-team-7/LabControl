package com.pnu.lab.control.labcontrol.service;

import com.pnu.lab.control.labcontrol.api.dto.AttachedContentRequest;
import com.pnu.lab.control.labcontrol.domain.attached.content.AttachedContentBase;
import com.pnu.lab.control.labcontrol.repository.BaseAttachedContentRepository;

import java.util.List;

public abstract class AbstractAttachedContentService<T extends AttachedContentBase> extends AbstractBaseService<T> {

    public List<T> getAttachedContentList(AttachedContentRequest request) {
        return getRepository().getAttachedContentList(request.getPrimaryObjectId());
    }

    public void deleteByPrimaryObjectId(String primaryObjectId) {
        getRepository().deleteByPrimaryObjectId(primaryObjectId);
    }

    public abstract BaseAttachedContentRepository<T> getRepository();

}
