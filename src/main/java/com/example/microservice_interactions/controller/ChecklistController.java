package com.example.microservice_interactions.controller;

import com.example.microservice_interactions.dto.ChecklistRequestDTO;
import com.example.microservice_interactions.entity.Checklist;
import com.example.microservice_interactions.repository.ChecklistRepository;
import com.example.microservice_interactions.service.ChecklistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<String> adicionarChecklist(@RequestBody ChecklistRequestDTO checklistRequestDTO) {
        checklistService.addChecklist(
                checklistRequestDTO.userId(),
                checklistRequestDTO.bookId()
        );
        return ResponseEntity.ok("Adicionado à sua checklist com sucesso");
    }

    @DeleteMapping(value = "/removerChecklist")
    public ResponseEntity<String> removerChecklist(@RequestBody ChecklistRequestDTO checklistRequestDTO) {
        checklistService.removeChecklist(checklistRequestDTO.userId(), checklistRequestDTO.bookId());
        return ResponseEntity.ok("Removido da checklist com sucesso");
    }

    @GetMapping("/checklistUsuario/{userId}")
    public ResponseEntity<List<Checklist>> listarChecklistsPorUsuario(@PathVariable Long userId) {
        List<Checklist> checklists = checklistRepository.findByUserId(userId);

        if (checklists.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(checklists);
    }

}
