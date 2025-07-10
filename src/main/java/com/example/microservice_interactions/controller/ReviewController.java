package com.example.microservice_interactions.controller;

import com.example.microservice_interactions.dto.EditReviewDTO;
import com.example.microservice_interactions.dto.ReviewRequestDTO;
import com.example.microservice_interactions.repository.ReviewRepository;
import com.example.microservice_interactions.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ReviewRepository reviewRepository;

    @PostMapping(value = "/adicionarReview")
    public ResponseEntity<String> adicionarReview(@RequestBody ReviewRequestDTO dto){
        reviewService.addReview(dto.userId(), dto.bookId(), dto.rating(), dto.title(), dto.comment());
        return ResponseEntity.ok("Review adicionada");
    }

    @PutMapping(value = "/editarReview")
    public ResponseEntity<String> editarReview(@RequestBody EditReviewDTO dto) {
        reviewService.editReview(dto.reviewId(), dto.newRating(), dto.newTitle(), dto.newComment());
        return ResponseEntity.ok("Review alterada");
    }

    @DeleteMapping(value = "removerReview/{reviewId}")
    public ResponseEntity<String>  removerReview(@PathVariable Long reviewId) {
        reviewService.removerReview(reviewId);
        return ResponseEntity.ok("Review removida");


    }
}
