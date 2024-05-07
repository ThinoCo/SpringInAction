package co.thino.tacocloud.web;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import co.thino.tacocloud.Ingredient;
import co.thino.tacocloud.Ingredient.Type;
import co.thino.tacocloud.data.IngredientRepository;
import jakarta.validation.Valid;
import co.thino.tacocloud.Taco;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;


@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
public class DesignTacoController {

  private final IngredientRepository ingredientRepo;

  @Autowired
  public DesignTacoController(IngredientRepository ingredientRepo) {
    this.ingredientRepo = ingredientRepo;

  }

  @GetMapping
  public String showDesign(Model model) {
    model.addAttribute("taco", new Taco());
    return "design";
  }

@ModelAttribute
  public void addIngredientsToModel(Model model) {
    /*
    List<Ingredient> ingredients = Arrays.asList(
        new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
        new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
        new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
        new Ingredient("CARN", "Carnitas", Type.PROTEIN),
        new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
        new Ingredient("LETC", "Lettuce", Type.VEGGIES),
        new Ingredient("CHED", "Cheddar", Type.CHEESE),
        new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
        new Ingredient("SLSA", "Salsa", Type.SAUCE),
        new Ingredient("SRCR", "Sour Cream", Type.SAUCE)
    );
    Type[] types = Ingredient.Type.values();
    for (Type type : types) {
      model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
    }
    */
    
    Iterable<Ingredient> ingredients = ingredientRepo.findAll();
    Type[] types = Ingredient.Type.values();
    for (Type type : types) {
      model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
    }
  }
  
  private Iterable<Ingredient> filterByType(
      Iterable<Ingredient> ingredients, Type type) {
    return StreamSupport.stream(ingredients.spliterator(), false).
        filter(x -> x.getType().equals(type))
        .collect(Collectors.toList());

  }

  @PostMapping()
  public String processTaco(@Valid @ModelAttribute("taco") Taco taco, Errors errors) {
    if (errors.hasErrors()) {
      return "design";
    }
      log.info("Processing taco:" + taco);
      return "redirect:/orders/current";
  }
  
}