package com.example.microservice_interactions.controller;

import com.example.microservice_interactions.entity.Checklist;
import com.example.microservice_interactions.entity.Like;
import com.example.microservice_interactions.repository.ChecklistRepository;
import com.example.microservice_interactions.service.ChecklistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/checklist")
public class ChecklistController {

    @Autowired
    private ChecklistService checklistService;

    @Autowired
    private ChecklistRepository checklistRepository;

    @PostMapping(value = "/adicionarChecklist")
    public ResponseEntity<String> adicionarChecklist(@RequestParam Long userId,@RequestParam Long bookId){
         checklistService.addChecklist(userId,bookId);
         return ResponseEntity.ok("Adicionado com sucesso");
    }

}
