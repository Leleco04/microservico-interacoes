package com.example.microservice_interactions.controller;

import com.example.microservice_interactions.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ReviewController reviewController;

    @PostMapping(value = "/adicionarReview")
    public ResponseEntity<String> adicionarReview(@RequestParam Long userId,@RequestParam Long bookId,@RequestParam int rating,
                                                  @RequestParam String title,@RequestParam String comment){
        reviewService.addReview(userId, bookId, rating, title, comment);
        return ResponseEntity.ok("Review adicionada");
    }

    @PutMapping(value = "/editarReview")
    public ResponseEntity<String> editarReview(@RequestParam Long reviewId,@RequestParam int newRating,
                                               @RequestParam String newTitle,@RequestParam String newComment){
        reviewService.editReview(reviewId,newRating,newTitle,newComment);
        return ResponseEntity.ok("Review alterada");
    }

    @DeleteMapping()
    public ResponseEntity<String> removerReview(@RequestParam Long userId,@RequestParam Long bookId,
                                                @RequestParam Long reviewId){
        reviewService.removeReview(userId, bookId, reviewId);
        return ResponseEntity.ok("Review removida");
    }
}
