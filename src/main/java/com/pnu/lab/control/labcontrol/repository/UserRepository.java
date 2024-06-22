package com.pnu.lab.control.labcontrol.repository;

import com.pnu.lab.control.labcontrol.domain.QUser;
import com.pnu.lab.control.labcontrol.domain.User;
import com.querydsl.core.types.dsl.EntityPathBase;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends BaseRepository<User> {

    Optional<User> getUserByEmail(String email);

    @Override
    default EntityPathBase<User> getQEntity() {
        return QUser.user;
    }
}
