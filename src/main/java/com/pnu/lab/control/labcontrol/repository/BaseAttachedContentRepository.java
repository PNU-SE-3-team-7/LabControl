package com.pnu.lab.control.labcontrol.repository;

import com.pnu.lab.control.labcontrol.domain.attached.content.AttachedContentBase;
import com.pnu.lab.control.labcontrol.utils.QueryDslFactory;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface BaseAttachedContentRepository<T extends AttachedContentBase> extends BaseSearchRepository<T> {

    default List<T> getAttachedContentList(String primaryObjectId) {
        return QueryDslFactory.getQueryFactory()
                .selectFrom(getQEntity())
                .where(getPrimaryObjectIdPath().eq(primaryObjectId))
                .fetch();
    }

    default void deleteByPrimaryObjectId(String primaryObjectId) {
        QueryDslFactory.getQueryFactory()
                .delete(getQEntity())
                .where(getPrimaryObjectIdPath().eq(primaryObjectId))
                .execute();
    }

    StringPath getPrimaryObjectIdPath();

}
