package org.ahmedukamel.eduai.controller.reports;

import org.ahmedukamel.eduai.model.Suggestion;
import org.ahmedukamel.eduai.service.reports.SuggestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/suggestions")
public class SuggestionController {

    @Autowired
    private SuggestionService suggestionService;



    @PostMapping
    public ResponseEntity<Suggestion> createSuggestion(@RequestBody Suggestion suggestion) {
        Suggestion createdSuggestion = suggestionService.createSuggestion(suggestion);
        return new ResponseEntity<>(createdSuggestion, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Suggestion> updateSuggestion(@PathVariable int id, @RequestBody Suggestion suggestionDetails) {
        Suggestion updatedSuggestion = suggestionService.updateSuggestion(id, suggestionDetails);
        return ResponseEntity.ok(updatedSuggestion);
    }
    @DeleteMapping("/{id}")
    public void deleteReport(@PathVariable int id) {
        suggestionService.deleteSuggestionById(id);
    }
}
