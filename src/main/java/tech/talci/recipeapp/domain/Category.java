package tech.talci.recipeapp.domain;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Document
public class Category {

    @Id
    private String id;
    private String description;

    @DBRef
    private Set<Recipe> recipes;

    public String getCategoryName() {
        return description;
    }

    public void setCategoryName(String categoryName) {
        this.description = categoryName;
    }

}
