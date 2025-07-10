package com.example.microservice_interactions.controller;

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
         return ResponseEntity.ok("Adicionado a sua checklist com sucesso");
    }

    @PutMapping(value = "/editarChecklist")
    public ResponseEntity<String> editarChecklist(@RequestParam Long userId,@RequestParam Long oldBookId,@RequestParam Long newBookId){
        checklistService.editChecklist(userId, oldBookId, newBookId);
        return ResponseEntity.ok("Editado a checklist com sucesso");
    }


    @DeleteMapping(value = "/removerChecklist/{bookId}")
    public ResponseEntity<String> removerChecklist (@PathVariable("userId")Long userId, @PathVariable("bookId")Long bookId){
        checklistService.removeChecklist(userId,bookId);
        return ResponseEntity.ok("Removido da checklist com sucesso");
    }

}
