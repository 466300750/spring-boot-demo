package com.example.user;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserApplicationService applicationService;

    public UserController(UserApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @RequestMapping(method = RequestMethod.POST)
    @PreAuthorize(value = "hasRole('ADMIN')")
    public void addUser(@RequestBody User user) {
        applicationService.saveUser(user);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @PreAuthorize(value = "hasRole('ADMIN')")
    public void deleteUser(@PathVariable("id") long id) {
        applicationService.deleteUser(id);
    }

    @PreAuthorize(value = "hasRole('ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User getUser(@PathVariable("id") long id) {
        return applicationService.getUser(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    @PreAuthorize(value = "hasRole('ADMIN')")
    public List<User> getUser() {
        return applicationService.getUserList();
    }
}
