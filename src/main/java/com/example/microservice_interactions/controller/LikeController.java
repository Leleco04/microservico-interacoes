package com.example.microservice_interactions.controller;

import com.example.microservice_interactions.entity.Checklist;
import com.example.microservice_interactions.entity.Like;
import com.example.microservice_interactions.repository.LikeRepository;
import com.example.microservice_interactions.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/like")
@CrossOrigin(origins = "http://localhost:4200")
public class LikeController {

    @Autowired
    private LikeService likeService;

    @Autowired
    private LikeRepository likeRepository;

    @PostMapping(value = "/{userId}/{bookId}")
    public ResponseEntity<String> adicionarLike(@PathVariable("userId") Long userId, @PathVariable("bookId") Long bookId){
        likeService.addLike(userId,bookId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/removerLike/usuario/{userId}/livro/{bookId}")
    public ResponseEntity<String> removerLike(@PathVariable("userId") Long userId, @PathVariable("bookId") Long bookId){
        likeService.removeLike(userId, bookId);
        return ResponseEntity.ok("Removido com sucesso");
    }

    @GetMapping(value = "/{userId}")
    public ResponseEntity<List<Like>> listarLikesPorUsuario(@PathVariable Long userId){
        List<Like> likes = likeRepository.findByUserId(userId);

        if (likes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(likes);
    }

    @GetMapping(value = "/existe/{userId}/{bookId}")
    public ResponseEntity<String> verificarExiste(@PathVariable Long userId, @PathVariable("bookId") Long bookId){
        if(likeService.existe(userId, bookId)) {
            return ResponseEntity.ok("Like encontrado!");
        }
        return ResponseEntity.notFound().build();
    }

}
