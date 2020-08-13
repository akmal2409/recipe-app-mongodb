package tech.talci.recipeapp.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
public class Notes {

    @Id
    private String id;
    private Recipe recipe; // OneToOne
    private String recipeNotes;

    public Notes() {
    }

}
