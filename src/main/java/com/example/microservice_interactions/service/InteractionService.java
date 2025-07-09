package com.example.microservice_interactions.service;

import com.example.microservice_interactions.entity.Like;
import com.example.microservice_interactions.entity.Review;
import com.example.microservice_interactions.repository.LikeRepository;
import com.example.microservice_interactions.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class InteractionService {

    @Autowired
    private LikeRepository likeRepository;

    public void addLike(Long userId, Long bookId) {
        if(!likeRepository.existsByUserIdAndBookId(userId, bookId)) {
            Like like = new Like(userId, bookId);
            likeRepository.save(like);
        }
    }

    public void editLike(Long userId, Long bookId, String newReactionType) {
        Optional<Like> likeOpt = likeRepository.findByUserIdAndBookId(userId, bookId);
        if (likeOpt.isPresent()) {
            Like like = likeOpt.get();
            like.setReactionType(newReactionType);
            likeRepository.save(like);
        }
    }

    public void removeLike(Long userId, Long bookId) {
        likeRepository.findByUserIdAndBookId(userId, bookId)
                .ifPresent(likeRepository::delete);
    }

    @Autowired
    private ReviewRepository reviewRepository;

    public void addReview(Long userId, Long bookId, int rating, String title, String comment){
        Review review = new Review(userId,bookId,rating,title,comment, LocalDateTime.now());
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

    public void removeReview(Long userId, Long bookId) {
        reviewRepository.deleteByUserIdAndBookId(userId, bookId);
    }

}
