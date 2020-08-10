package tech.talci.recipeapp.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class Recipe {

    private String id;
    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String directions;
    private Byte[] image;
    private String Url;
    private Difficulty difficulty;
    private Notes notes; // OneToOne
    private Set<Ingredient> ingredients = new HashSet<>();

    private Set<Category> categories = new HashSet<>();

    public void setNotes(Notes notes) {
        this.notes = notes;
    }

    public Recipe addIngredient(Ingredient ingredient){
        ingredient.setRecipe(this);
        this.ingredients.add(ingredient);
        return this;
    }


}
