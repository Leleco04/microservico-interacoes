package com.example.microservice_interactions.service;

import com.example.microservice_interactions.entity.Like;
import com.example.microservice_interactions.entity.Review;
import com.example.microservice_interactions.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public void addReview(Long userId, Long bookId, int rating, String title, String comment){
        Review review = new Review(userId, bookId, rating, title, comment, LocalDateTime.now());
        reviewRepository.save(review);
    }

    public void editReview(Long reviewId, int newRating, String newTitle, String newComment) {
        Optional<Review> optionalReview = reviewRepository.findById(reviewId);
        if (optionalReview.isPresent()) {
            Review review = optionalReview.get();
            review.setRating(newRating);
            review.setTitle(newTitle);
            review.setComment(newComment);
            reviewRepository.save(review);
        } else {
            throw new RuntimeException("Review não encontrada com id " + reviewId);
        }
    }

    public void removerReview(Long reviewId) {
        if (reviewRepository.existsById(reviewId)) {
            reviewRepository.deleteById(reviewId);
        } else {
            throw new RuntimeException("Review não encontrada com id " + reviewId);
        }
    }

    public List<Review> listarReview(Long userId) {
        return reviewRepository.findByUserId(userId);
    }

}
