package com.vitali.runstats.repo;

import com.vitali.runstats.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Long> {
    User findUserByLogin(String login);
}
