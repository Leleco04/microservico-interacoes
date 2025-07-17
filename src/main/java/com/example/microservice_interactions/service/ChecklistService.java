package com.example.microservice_interactions.service;

import com.example.microservice_interactions.entity.Checklist;
import com.example.microservice_interactions.repository.ChecklistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.microservice_interactions.enums.Status;

import java.util.List;
import java.util.Optional;

@Service
public class ChecklistService {

    @Autowired
    private ChecklistRepository checklistRepository;


    public void addChecklist(Long userId, Long bookId, String statusStr) {
        try {
            Status status = Status.valueOf(statusStr.toUpperCase()); // converte a String para o enum
            Checklist checklist = new Checklist();
            checklist.setUserId(userId);
            checklist.setBookId(bookId);
            checklist.setStatus(status);
            checklistRepository.save(checklist);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Status inválido. Use: READ ou WANT_TO_READ");
        }
    }

    public void editChecklist(Long userId, Long oldBookId, Long newBookId, Status newStatus) {
        Optional<Checklist> optionalChecklist = checklistRepository.findByUserIdAndBookId(userId, oldBookId);

        if (optionalChecklist.isPresent()) {
            Checklist checklist = optionalChecklist.get();
            checklist.setBookId(newBookId);
            checklist.setStatus(newStatus);
            checklistRepository.save(checklist);
        } else {
            throw new RuntimeException("Checklist não encontrada para userId " + userId + " e bookId " + oldBookId);
        }
    }


    public void removeChecklist(Long userId, Long bookId){
        checklistRepository.findByUserIdAndBookId(userId, bookId)
                .ifPresent(checklistRepository::delete);

    }

    public List<Checklist> listarChecklist(Long userId,Status status) {
        return checklistRepository.findByUserIdAndStatus(userId, status);
    }

}
