package com.pnu.lab.control.labcontrol.service;

import com.pnu.lab.control.labcontrol.domain.BaseEntity;
import com.pnu.lab.control.labcontrol.exception.EntityNotFoundException;
import com.pnu.lab.control.labcontrol.repository.BaseRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public abstract class AbstractBaseService<T extends BaseEntity> {

    public T findOne(String id) {
        return getRepository().findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, getType()));
    }

    public T create(T entity) {
        return getRepository().save(entity);
    }

    public T update(T entity) {
        return getRepository().save(entity);
    }

    public void delete(String id) {
        delete(findOne(id));
    }

    public void delete(T entity) {
        getRepository().delete(entity);
    }

    public abstract Class<T> getType();

    public abstract BaseRepository<T> getRepository();

}
