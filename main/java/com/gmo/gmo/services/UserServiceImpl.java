package com.gmo.gmo.services;

import com.gmo.gmo.entities.User;
import com.gmo.gmo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    private UserRepository repository;
    @Autowired
    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }
    @Override
    public boolean login(User user) {
        String userName = user.getUserName();
        String password = user.getPassword();
        int maxLenUser = 20;
        int maxLenPass = 15;
        int minLenPass = 6;
        User currentUser = repository.findByUserName(userName);

        if (currentUser == null ||
            !checkSingleByte(userName) ||
            !checkSingleByte(password) ||
            userName.isEmpty() ||
            password.isEmpty() ||
            userName.length() >= maxLenUser ||
            (password.length() > maxLenPass ||
            password.length() < minLenPass)
            ){
            return false;
        }
        return currentUser.getPassword().equals(password);
    }

    private static boolean checkSingleByte(String input){
        char[] arr = input.toCharArray();
        for(Character c:arr){
            if (c > 127 ) return false;
        }
        return true;
    }


    @Override
    public User save(User user) {
        if (repository.findByUserName(user.getUserName()) == null){
            return repository.save(user);
        }
        return null;
    }

}
