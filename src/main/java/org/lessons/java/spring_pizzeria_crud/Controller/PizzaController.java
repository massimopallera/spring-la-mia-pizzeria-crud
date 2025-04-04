package org.lessons.java.spring_pizzeria_crud.Controller;

import java.util.List;
import java.util.NoSuchElementException;
import org.lessons.java.spring_pizzeria_crud.Model.Pizza;
import org.lessons.java.spring_pizzeria_crud.Repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/pizze")
public class PizzaController {
    
    @Autowired
    private PizzaRepository repo;


    // * Return index page with all elements
    @GetMapping
    public String index(Model model) {

        // * Create List of pizzas
        List<Pizza> pizze = repo.findAll(); 
        model.addAttribute("pizze", pizze); 

        return "pizze/index";
    }

    // * Return one element
    @GetMapping("/{id}")
    public String show(Model model, @PathVariable("id") Integer id ) {
        // * Search for element id
        try{
            Pizza pizza = repo.findById(id).get();
            model.addAttribute("pizza", pizza);
        } catch (NoSuchElementException e) {
            // ? NoSuchElementException is thrown when the id is not found.
            // * If tryCatch catch this Exception pizza in model will be null
            model.addAttribute("pizza", null);
        }
        return "pizze/show";
    }
    
    // * Return form to create new Pizza, model will get a new istance for Pizza
    @GetMapping("/create")
    public String returnForm(Model model) {

        model.addAttribute("pizza", new Pizza());

        return "/pizze/create";
    }

    // * After form submit, pizza has his attributes. After checking all fields, new istance will be saved in db
    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("pizza") Pizza pizzaForm, BindingResult br, Model model){

        // * Check errors
        if (br.hasErrors()) {
            model.addAttribute("pizza", pizzaForm);
            return "pizze/create";
        }

        if(pizzaForm.getPhotoUrl() == null) pizzaForm.setPhotoUrl("https://placehold.co/300"); // ! To change. It must be insert from user

        repo.save(pizzaForm); // * Save pizza in db

        return "redirect:/pizze"; // * Redirect user to index page 
    }


    // * Give the edit form page, with fields already filled by the element that user want to edit
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Integer id, Model model) {

        // * Search for Pizza in db
        try {
            Pizza toEdit = repo.findById(id).get();
            toEdit.setId(id);
            model.addAttribute("pizza", toEdit);
        } catch (NoSuchElementException e) {
            model.addAttribute("pizza", null);
        }

        return "/pizze/edit";
    }
    
    // * Same method of create
    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("pizza") Pizza pizzaForm, BindingResult br, Model model){

        if (br.hasErrors()) {
            model.addAttribute("pizza", pizzaForm);
            return "pizze/edit";
        }

        //? Check if photoUrl is not null, else give it a default link
        if(pizzaForm.getPhotoUrl() == null) pizzaForm.setPhotoUrl("https://placehold.co/300"); 

        repo.save(pizzaForm);

        return "redirect:/pizze";
    }


    // * Delete element
    @PostMapping("/delete/{id}")
    public String destroy(@PathVariable Integer id) {
        
        repo.deleteById(id);

        return "redirect:/pizze";
    }
    
}
