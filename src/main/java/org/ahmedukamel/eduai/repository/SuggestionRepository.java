package org.ahmedukamel.eduai.repository;

import org.ahmedukamel.eduai.model.Suggestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SuggestionRepository extends JpaRepository<Suggestion, Integer> {
}