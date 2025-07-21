package com.example.microservice_interactions.service;

import com.example.microservice_interactions.client.BookClient;
import com.example.microservice_interactions.client.UserClient;
import com.example.microservice_interactions.dto.BookDTO;
import com.example.microservice_interactions.dto.ReviewBookResponseDTO;
import com.example.microservice_interactions.dto.ReviewResponseDTO;
import com.example.microservice_interactions.entity.Like;
import com.example.microservice_interactions.entity.Review;
import com.example.microservice_interactions.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    private final UserClient userClient;
    private final BookClient bookClient;

    public ReviewService(UserClient userClient, BookClient bookClient) {
        this.userClient = userClient;
        this.bookClient = bookClient;
    }

    public void addReview(Long userId, Long bookId, int rating, String title, String comment, String username){
        userClient.findUserById(userId);
        bookClient.findBookById(bookId);

        Review review = new Review(userId, bookId, rating, title, comment, username, LocalDateTime.now());
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

    // No seu ReviewService.java
    public List<ReviewBookResponseDTO> findReviewsByUserId(Long userId) {
        // 1. Busca as reviews básicas do seu banco de dados
        List<Review> reviews = reviewRepository.findByUserId(userId);

        // 2. Mapeia cada review para o DTO de resposta completo
        return reviews.stream()
                .map(review -> {
                    // 3. Para cada review, chama o BookService via Feign para buscar os detalhes do livro
                    BookDTO bookDetails = bookClient.findBookById(review.getBookId());

                    // 4. Monta o DTO de resposta combinado e o retorna
                    return new ReviewBookResponseDTO(
                            review.getUserId(),
                            review.getBookId(),
                            review.getRating(),
                            review.getTitle(),
                            review.getComment(),
                            review.getUsername(),
                            bookDetails,
                            review.getCreatedAt()
                    );
                })
                .collect(Collectors.toList());
    }

    public List<Review> listarReview(Long userId) {
        return reviewRepository.findByUserId(userId);
    }

    public ReviewResponseDTO getReviewsAndStatsByBookId(Long bookId) {
        // Pega a lista de reviews
        List<Review> reviews = reviewRepository.findByBookId(bookId);

        // Faz a contagem de reviews por livro
        long totalCount = reviewRepository.countByBookId(bookId);

        // Busca a média de avaliações dos usuários
        Double averageRating = reviewRepository.findAverageRatingByBookId(bookId);
        // Caso a busca por média retorne null, a média vira 0
        double finalAverage = (averageRating == null) ? 0.0 : averageRating;

        // Monta e retorna o DTO de resposta
        return new ReviewResponseDTO(reviews, totalCount, finalAverage);
    }

}
