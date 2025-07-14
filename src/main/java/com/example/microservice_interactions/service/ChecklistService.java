package com.example.microservice_interactions.service;

import com.example.microservice_interactions.entity.Checklist;
import com.example.microservice_interactions.repository.ChecklistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChecklistService {

    @Autowired
    private ChecklistRepository checklistRepository;


    public void addChecklist(Long userId, Long bookId) {
        Checklist checklist = new Checklist();
        checklist.setUserId(userId);
        checklist.setBookId(bookId);
        checklistRepository.save(checklist);
    }

    public void editChecklist(Long userId, Long oldBookId, Long newBookId) {
        Optional<Checklist> optionalChecklist = checklistRepository.findByUserIdAndBookId(userId, oldBookId);

        if (optionalChecklist.isPresent()) {
            Checklist checklist = optionalChecklist.get();
            checklist.setBookId(newBookId);
            checklistRepository.save(checklist);
        } else {
            throw new RuntimeException("Checklist não encontrada para userId " + userId + " e bookId " + oldBookId);
        }
    }


    public void removeChecklist(Long userId, Long bookId){
        checklistRepository.findByUserIdAndBookId(userId, bookId)
                .ifPresent(checklistRepository::delete);

    }

    public List<Checklist> listarChecklist(Long userId) {
        return checklistRepository.findByUserId(userId);
    }

}
