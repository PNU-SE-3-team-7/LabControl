package com.pnu.lab.control.labcontrol.repository;

import com.pnu.lab.control.labcontrol.api.dto.SearchRequest;
import com.pnu.lab.control.labcontrol.domain.BaseEntity;
import com.pnu.lab.control.labcontrol.utils.QueryDslFactory;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface BaseSearchRepository<T extends BaseEntity> extends BaseRepository<T> {

    default List<T> getList(SearchRequest searchRequest) {
        return QueryDslFactory.getQueryFactory()
                .selectFrom(getQEntity())
                .limit(searchRequest.getLimit())
                .offset(searchRequest.getOffset())
                .fetch();
    }
}
