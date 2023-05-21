package com.glitch.unitoki.service;

import com.glitch.unitoki.model.User;
import com.glitch.unitoki.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User save(User user) {
        return userRepository.save(user);
    }

    public User findByUserWallet(String userWallet) {
        return userRepository.findByUserWallet(userWallet);
    }

    public List<User> getTopPointUsers() {
        return userRepository.findAll(Sort.by(Sort.Direction.ASC, "point"));
    }
}