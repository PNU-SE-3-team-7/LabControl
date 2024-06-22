package com.pnu.lab.control.labcontrol.service;

import com.pnu.lab.control.labcontrol.domain.User;
import com.pnu.lab.control.labcontrol.repository.BaseRepository;
import com.pnu.lab.control.labcontrol.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService extends AbstractBaseService<User> {

    private final UserRepository repository;

    public Optional<User> getByEmail(String email) {
        return repository.getUserByEmail(email);
    }

    @Override
    public Class<User> getType() {
        return User.class;
    }

    @Override
    public BaseRepository<User> getRepository() {
        return repository;
    }
}
