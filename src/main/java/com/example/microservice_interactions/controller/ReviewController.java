package com.example.microservice_interactions.controller;

import com.example.microservice_interactions.client.BookClient;
import com.example.microservice_interactions.client.UserClient;
import com.example.microservice_interactions.dto.*;
import com.example.microservice_interactions.entity.Like;
import com.example.microservice_interactions.entity.Review;
import com.example.microservice_interactions.repository.ReviewRepository;
import com.example.microservice_interactions.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/review")
@CrossOrigin(origins = "http://localhost:4200")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ReviewRepository reviewRepository;

    @PostMapping(value = "/add")
    public ResponseEntity<ReviewRequestDTO> adicionarReview(@RequestBody ReviewRequestDTO dto){
        reviewService.addReview(dto.userId(), dto.bookId(), dto.rating(), dto.title(), dto.comment(), dto.username());
        return ResponseEntity.ok().body(dto);
    }

    @PutMapping(value = "/editarReview")
    public ResponseEntity<String> editarReview(@RequestBody EditReviewDTO dto) {
        reviewService.editReview(dto.reviewId(), dto.newRating(), dto.newTitle(), dto.newComment());
        return ResponseEntity.ok("Review alterada");
    }

    @DeleteMapping(value = "/{reviewId}")
    public ResponseEntity<String>  removerReview(@PathVariable Long reviewId) {
        reviewService.removerReview(reviewId);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/book/{bookId}")
    public ResponseEntity<ReviewResponseDTO> getByBookId(
            @PathVariable Long bookId,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size
    ){
        ReviewResponseDTO response = reviewService.getReviewsAndStatsByBookId(bookId, page, size);

        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/user/{userId}")
    public ResponseEntity<List<ReviewBookResponseDTO>> listarReviews(@PathVariable Long userId){
        List<ReviewBookResponseDTO> reviews = reviewService.findReviewsByUserId(userId);

        if (reviews.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok().body(reviews);
    }
}
