package com.example.microservice_interactions.controller;

import com.example.microservice_interactions.repository.LikeRepository;
import com.example.microservice_interactions.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/likes")
public class LikeController {

    @Autowired
    private LikeService likeService;

    @Autowired
    private LikeRepository likeRepository;

    @PostMapping(value = "/adicionarLike/usuario/{userId}/livro/{bookId}")
    public ResponseEntity<String> adicionarLike(@PathVariable("userId") Long userId, @PathVariable("bookId") Long bookId){
        likeService.addLike(userId,bookId);
        return ResponseEntity.ok("Like adicionado");
    }

    @DeleteMapping(value = "/removerLike/usuario/{userId}/livro/{bookId}")
    public ResponseEntity<String> removerLike(@PathVariable("userId") Long userId, @PathVariable("bookId") Long bookId){
        likeService.removeLike(userId, bookId);
        return ResponseEntity.ok("Removido com sucesso");
    }

}
