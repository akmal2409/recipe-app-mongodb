package tech.talci.recipeapp.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(exclude = {"recipes"})
public class Category {

    private String id;
    private String description;
    private Set<Recipe> recipes;

    public String getCategoryName() {
        return description;
    }

    public void setCategoryName(String categoryName) {
        this.description = categoryName;
    }

}
