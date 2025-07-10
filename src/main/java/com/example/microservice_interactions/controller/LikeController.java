package com.example.microservice_interactions.controller;

import com.example.microservice_interactions.repository.LikeRepository;
import com.example.microservice_interactions.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class LikeController {

    @Autowired
    private LikeService likeService;

    @Autowired
    private LikeRepository likeRepository;

    @PostMapping(value = "/adicionarLike")
    public ResponseEntity<String> adicionarLike(@RequestParam Long userId, @RequestParam Long bookId){
        likeService.addLike(userId,bookId);
        return ResponseEntity.ok("Like adicionado");
    }

    @DeleteMapping(value = "/removerLike")
    public ResponseEntity<String> removerLike(@RequestParam Long userId, @RequestParam Long bookId){
        likeService.removeLike(userId, bookId);
        return ResponseEntity.ok("Removido com sucesso");
    }

}
