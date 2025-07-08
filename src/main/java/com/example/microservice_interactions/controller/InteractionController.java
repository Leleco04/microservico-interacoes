package com.example.microservice_interactions.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/interaction")
public class InteractionController {

    @PostMapping("/like/{bookId}")
    public String likeBook(@PathVariable Long bookId, Authentication authentication) {
        Long userId = Long.parseLong(authentication.getName());
        return "Ok";
    }

}