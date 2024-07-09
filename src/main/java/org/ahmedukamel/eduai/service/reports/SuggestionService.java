package org.ahmedukamel.eduai.service.reports;

import org.ahmedukamel.eduai.model.Suggestion;
import org.ahmedukamel.eduai.repository.SuggestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SuggestionService {
    @Autowired
    private SuggestionRepository suggestionRepository;

    public List<Suggestion> getAllSuggestions() {
        return suggestionRepository.findAll();
    }


    public Suggestion createSuggestion(Suggestion suggestion) {
        return suggestionRepository.save(suggestion);
    }
    public Suggestion getSuggestionById(int id) {
        return suggestionRepository.findById(id).get();
    }
    public void deleteSuggestionById(int id) {
        suggestionRepository.deleteById(id);
    }
    public Suggestion updateSuggestion(int id, Suggestion updatedSuggestion) {
        Optional<Suggestion> optionalSuggestion = suggestionRepository.findById(id);
        if (optionalSuggestion.isPresent()) {
            Suggestion existingSuggestion = optionalSuggestion.get();
            existingSuggestion.setUserName(updatedSuggestion.getUserName());
            existingSuggestion.setAvatarUrl(updatedSuggestion.getAvatarUrl());
            existingSuggestion.setTitle(updatedSuggestion.getTitle());
            existingSuggestion.setDescription(updatedSuggestion.getDescription());
            existingSuggestion.setDate(updatedSuggestion.getDate());
            return suggestionRepository.save(existingSuggestion);
        } else {
            throw new RuntimeException("Suggestion not found");
        }

    }
}