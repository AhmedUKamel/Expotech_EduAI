package org.ahmedukamel.eduai.model.embeddable;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Name {
    private String first;
    private String last;

    @Override
    public String toString() {
        return first + " " + last;
    }
}