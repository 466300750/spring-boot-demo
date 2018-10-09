package com.example.user;

import com.example.BaseApiTest;
import com.example.security.user.Role;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class UserControllerTest extends BaseApiTest {

    @Test
    public void should_add_user_successfully() {
        User user = new User();
        user.setAccount("account");
        user.setName("name");
        user.setPassword("password");
        user.setEmail("email");
        user.setPhone("phone");
        List<Role> roles = new ArrayList<>();
        roles.add(new Role(1L, "USER"));
        user.setRoles(roles);

        ResponseEntity<Void> response = testRestTemplate.postForEntity("/user", constructEntity(admin_token, user), Void.class);
        int status = response.getStatusCodeValue();
        assertEquals(200, status);

        ResponseEntity<List<User>> response1 = testRestTemplate.exchange("/user", HttpMethod.GET, constructEntity(admin_token, null), new ParameterizedTypeReference<List<User>>() {});
        assertEquals(200, response1.getStatusCodeValue());
        List<User> userList = response1.getBody();
        assertThat(userList.size(), is(4));

        long newUserId = userList.get(3).getId();
        ResponseEntity<Void> response2 = testRestTemplate.exchange("/user/{id}", HttpMethod.DELETE, constructEntity(admin_token, null), new ParameterizedTypeReference<Void>() {}, newUserId);
        assertEquals(200, response2.getStatusCodeValue());
    }

}