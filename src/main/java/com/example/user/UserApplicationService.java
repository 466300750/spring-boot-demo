package com.example.user;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserApplicationService {
    private UserService userService;

    public UserApplicationService(UserService userService) {
        this.userService = userService;
    }

    @Transactional
    public void saveUser(User user) {
        userService.saveUser(user);
    }

    @Transactional
    public void deleteUser(long id) {
        userService.deleteUser(id);
    }

    @Transactional
    public User getUser(Long id) {
        return userService.getUser(id);
    }

    @Transactional
    public List<User> getUserList() {
        return userService.listUser();
    }
}
