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
            Status status = Status.valueOf(statusStr.toUpperCase());
            if (checklistRepository.existsByUserIdAndBookId(userId, bookId)) {
                Checklist checklistAtual = getChecklist(userId, bookId);

                if (checklistAtual.getStatus() != status) {
                    editChecklist(userId, bookId, status);
                }
            } else {
                Checklist checklist = new Checklist();
                checklist.setUserId(userId);
                checklist.setBookId(bookId);
                checklist.setStatus(status);
                checklistRepository.save(checklist);
            }
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Status inválido. Use: READ ou WANT_TO_READ");
        }
    }

    public void editChecklist(Long userId, Long bookId, Status newStatus) {
        Checklist checklistAtual = getChecklist(userId, bookId);

        try {
            if (checklistRepository.existsByUserIdAndBookId(userId, bookId)) {
                checklistAtual.setStatus(newStatus);
                checklistRepository.save(checklistAtual);
            }
        } catch (RuntimeException e) {
            throw new RuntimeException("Checklist não encontrada.");
        }
    }

    public Checklist getChecklist(Long userId, Long bookId) {
        return checklistRepository.findByUserIdAndBookId(userId, bookId).
                orElseThrow(() -> new RuntimeException("Livro não encontrado."));
    }

    public void removeChecklist(Long userId, Long bookId){
        checklistRepository.findByUserIdAndBookId(userId, bookId)
                .ifPresent(checklistRepository::delete);

    }

    public List<Checklist> listarChecklist(Long userId,Status status) {
        return checklistRepository.findByUserIdAndStatus(userId, status);
    }

}
