package com.pnu.lab.control.labcontrol.service;

import com.pnu.lab.control.labcontrol.api.dto.SearchRequest;
import com.pnu.lab.control.labcontrol.domain.BaseEntity;
import com.pnu.lab.control.labcontrol.repository.BaseSearchRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public abstract class AbstractSearchService<T extends BaseEntity> extends AbstractBaseService<T> {

    public List<T> getList(SearchRequest request) {
        return getRepository().getList(request);
    }

    public abstract BaseSearchRepository<T> getRepository();

}
