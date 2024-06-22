package com.pnu.lab.control.labcontrol.service;

import com.pnu.lab.control.labcontrol.domain.BaseEntity;
import com.pnu.lab.control.labcontrol.exception.EntityNotFoundException;
import com.pnu.lab.control.labcontrol.repository.BaseRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Transactional
public abstract class AbstractBaseService<T extends BaseEntity> {

    public T findOne(String id) {
        return getRepository().findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, getType()));
    }

    public boolean existsById(String id) {
        return getRepository().existsById(id);
    }

    public T create(T entity) {
        return create(List.of(entity)).getFirst();
    }

    public T update(T entity) {
        return update(List.of(entity)).getFirst();
    }

    public List<T> create(Collection<T> entities) {
        return getRepository().saveAll(entities);
    }

    public List<T> update(Collection<T> entities) {
        return getRepository().saveAll(entities);
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
