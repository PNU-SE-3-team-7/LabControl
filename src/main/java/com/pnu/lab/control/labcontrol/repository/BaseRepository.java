package com.pnu.lab.control.labcontrol.repository;

import com.pnu.lab.control.labcontrol.domain.BaseEntity;
import com.querydsl.core.types.dsl.EntityPathBase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository<T extends BaseEntity> extends JpaRepository<T, String> {

    EntityPathBase<T> getQEntity();

}
