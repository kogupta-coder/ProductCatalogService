package com.productCatalog.commons;

import com.productCatalog.dtos.UserDto;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthCommons {

    private RestTemplate restTemplate;

    public AuthCommons(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public UserDto validateToken(String tokenValue) {

        //Instead of hard coding the URL, we should get the list of IPs from
        //Eureka Server and send the request in load balanced manner.

        ResponseEntity<UserDto> responseEntity = restTemplate.getForEntity(
                "http://USERAUTHSERVICE/auth/validate/" + tokenValue,
                UserDto.class
        );

        return responseEntity.getBody();
    }
}