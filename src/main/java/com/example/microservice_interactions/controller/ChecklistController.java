package com.example.microservice_interactions.controller;

import com.example.microservice_interactions.dto.ChecklistRequestDTO;
import com.example.microservice_interactions.entity.Checklist;
import com.example.microservice_interactions.repository.ChecklistRepository;
import com.example.microservice_interactions.service.ChecklistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.example.microservice_interactions.enums.Status;

import java.util.List;

@RestController
@RequestMapping("/checklist")
@CrossOrigin(origins = "http://localhost:4200")
public class ChecklistController {

    @Autowired
    private ChecklistService checklistService;

    @Autowired
    private ChecklistRepository checklistRepository;

    @PostMapping(value = "/adicionarChecklist")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> adicionarChecklist(@RequestBody ChecklistRequestDTO checklistRequestDTO) {
        checklistService.addChecklist(
                checklistRequestDTO.userId(),
                checklistRequestDTO.bookId(),
                checklistRequestDTO.status()
        );
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/removerChecklist/{userId}/{bookId}")
    public ResponseEntity<?> removerChecklist(@PathVariable Long userId, @PathVariable Long bookId) {
        checklistService.removeChecklist(userId, bookId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/checklistUsuario/{userId}/{status}")
    public ResponseEntity<List<Checklist>> listarChecklistsPorUsuario(@PathVariable Long userId, @PathVariable Status status) {
        List<Checklist> checklists = checklistService.listarChecklist(userId,status);

        if (checklists.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(checklists);
    }

    @GetMapping("/checklistUsuario/{userId}")
    public ResponseEntity<List<Checklist>> listarChecklistsPorUsuario(@PathVariable Long userId) {
        List<Checklist> checklists = checklistRepository.findByUserId(userId);

        if (checklists.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(checklists);
    }

    @GetMapping("/{userId}/{bookId}")
    public ResponseEntity<Checklist> getChecklist(@PathVariable Long userId, @PathVariable Long bookId) {
        try {
            Checklist checklist = checklistService.getChecklist(userId, bookId);
            return ResponseEntity.ok(checklist);
        } catch (RuntimeException e) {
            return ResponseEntity.noContent().build();
        }
    }

}
