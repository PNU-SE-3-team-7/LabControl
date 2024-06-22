package com.pnu.lab.control.labcontrol.repository;

import com.pnu.lab.control.labcontrol.domain.QUser;
import com.pnu.lab.control.labcontrol.domain.User;
import com.querydsl.core.types.dsl.EntityPathBase;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepository<User> {

    @Override
    default EntityPathBase<User> getQEntity(){
        return QUser.user;
    }
}
