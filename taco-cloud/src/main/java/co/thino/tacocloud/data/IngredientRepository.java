package co.thino.tacocloud.data;

import java.util.Optional;

import co.thino.tacocloud.Ingredient;

public interface IngredientRepository {

    Iterable<Ingredient> findAll();

    Optional<Ingredient> findById(String id);

    Ingredient save(Ingredient ingredient);
    
}
