package com.example;

import com.example.security.jwt.JwtTokenService;
import com.example.security.jwt.MyUserDetailService;
import com.example.security.jwt.UserPrincipal;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static java.lang.String.format;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class BaseApiTest {

    public static final String ROLE_USER = "USER";
    public static final String ROLE_ADMIN = "ADMIN";


    @Autowired
    protected TestRestTemplate testRestTemplate;

    @Autowired
    protected JwtTokenService jwtTokenService;

    @Autowired
    MyUserDetailService myUserDetailService;

    protected String admin_token;
    protected String user_token;

    @Before
    public void setUp() {
        UserPrincipal adminPrincipal = (UserPrincipal) myUserDetailService.loadUserByUsername("admin1");
        UserPrincipal userPrincipal = (UserPrincipal) myUserDetailService.loadUserByUsername("user1");
        admin_token = jwtTokenService.generateToken(adminPrincipal);
        user_token = jwtTokenService.generateToken(userPrincipal);
    }

    protected HttpEntity<Object> constructEntity(String token, Object body) {
        HttpHeaders headers = new HttpHeaders();
        if (token != null) {
            headers.set("Authorization", format("Bearer %s", token));
        }
        return new HttpEntity<>(body, headers);
    }
}
