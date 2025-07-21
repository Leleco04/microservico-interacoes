package com.example.microservice_interactions.client;

import com.example.microservice_interactions.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service", url = "${user-service.url}")
public interface UserClient {

    @GetMapping("/user/{id}")
    UserDTO findUserById(@PathVariable("id") Long id);

}
