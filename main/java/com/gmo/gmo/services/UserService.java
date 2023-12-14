package com.gmo.gmo.services;

import com.gmo.gmo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserService{
    User save(User user);
    boolean login(User user);


}
