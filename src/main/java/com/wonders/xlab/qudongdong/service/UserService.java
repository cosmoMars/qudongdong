package com.wonders.xlab.qudongdong.service;

import com.wonders.xlab.qudongdong.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mars on 15/8/19.
 */
//@Service
@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public long count() {
        return userRepository.count();
    }

    public void deleteAll() {
        userRepository.deleteAll();
    }

}
