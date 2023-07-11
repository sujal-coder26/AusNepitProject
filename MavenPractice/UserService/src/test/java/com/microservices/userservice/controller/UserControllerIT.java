package com.microservices.userservice.controller;


import com.microservices.userservice.UserServiceApplication;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = UserServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class UserControllerIT {
    @LocalServerPort
    private int port;

    TestRestTemplate testRestTemplate = new TestRestTemplate ( );
    HttpHeaders headers = new HttpHeaders ( );


    @Test
    public void testGetUsers() throws JSONException{
        HttpEntity http = new HttpEntity (null, headers  );
        ResponseEntity<String> response = testRestTemplate.exchange(
                createURLWithPort("/users/10c49522-be41-4561-919f-cbb037e254cd"),
                HttpMethod.GET, http, String.class);
        String expected = "{\"id\":\"10c49522-be41-4561-919f-cbb037e254cd\",\"about\":\"Java Developer\",\"email\":\"praweshgautam26@gmail.com\", \"userName\": \"Madhav Gautam\"}";
        JSONAssert.assertEquals (expected, response.getBody (), false);

    }
    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
