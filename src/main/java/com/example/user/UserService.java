package com.example.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void saveUser(User user) {
        String account = user.getAccount();
        User userByAccount = getUserByAccount(account);
        if (userByAccount == null) {
            String password = user.getPassword();
            user.setPassword(passwordEncoder.encode(password).trim());
            userRepository.save(user);
        } else {
            throw new RuntimeException("user already exist in system");
        }
    }

    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    public User getUser(long id) {
        return userRepository.findUserById(id);
    }

    public User getUserByAccount(String account) {
        return userRepository.findByAccount(account);
    }

    public List<User> listUser() {
        return userRepository.findAll();
    }
}
