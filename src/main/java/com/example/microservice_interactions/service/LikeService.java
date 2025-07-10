package com.example.microservice_interactions.service;

import com.example.microservice_interactions.entity.Checklist;
import com.example.microservice_interactions.entity.Like;
import com.example.microservice_interactions.repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LikeService {

    @Autowired
    private LikeRepository likeRepository;

    public void addLike(Long userId, Long bookId) {
        if(!likeRepository.existsByUserIdAndBookId(userId, bookId)) {
            Like like = new Like(userId, bookId);
            likeRepository.save(like);
        }
    }

    public void removeLike(Long userId, Long bookId) {
        likeRepository.findByUserIdAndBookId(userId, bookId)
                .ifPresent(likeRepository::delete);
    }

    public List<Like> listarLike(Long userId) {
        return likeRepository.findByUserId(userId);
    }


}
