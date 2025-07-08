package com.example.microservice_interactions.service;

import com.example.microservice_interactions.entity.Like;
import com.example.microservice_interactions.repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
