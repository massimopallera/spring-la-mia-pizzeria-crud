package org.lessons.java.spring_pizzeria_crud.Controller;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequestMapping("/pizze")
public class PizzaController {
    
    @Autowired
    private PizzaRepository repo;

    @GetMapping
    public String index(Model model) {

        List<Pizza> pizze = repo.findAll();
        model.addAttribute("pizze", pizze);

        return "pizze/index";
    }

    @GetMapping("/{id}")
    public String show(Model model, @PathVariable("id") Integer id ) {
        try{
            Pizza pizza = repo.findById(id).get();
            model.addAttribute("pizza", pizza);
        } catch (NoSuchElementException e) {
            model.addAttribute("pizza", null);
        }
        return "pizze/show";
    }
    
    @GetMapping("/create")
    public String returnForm(Model model) {

        model.addAttribute("pizza", new Pizza());

        return "/pizze/create";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("pizza") Pizza pizzaForm, BindingResult br, Model model){

        if (br.hasErrors()) {
            model.addAttribute("pizza", pizzaForm);
            return "pizze/create";
        }

        pizzaForm.setPhotoUrl("https://placehold.co/300"); //TEMPORARY -- NEED TO UPLOAD PHOTOS

        repo.save(pizzaForm);

        return "redirect:/pizze";
    }



    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable("id") Integer id, Model model) {

        try {
            Pizza toEdit = repo.findById(id).get();
            toEdit.setId(id);
            model.addAttribute("pizza", toEdit);
        } catch (NoSuchElementException e) {
            model.addAttribute("pizza", null);
        }

        return "/pizze/edit";
    }
    
    
    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("pizza") Pizza pizzaForm, BindingResult br, Model model){

        if (br.hasErrors()) {
            model.addAttribute("pizza", pizzaForm);
            return "pizze/edit";
        }

        if(pizzaForm.getPhotoUrl() == null) pizzaForm.setPhotoUrl("https://placehold.co/300"); //TEMPORARY -- NEED TO UPLOAD PHOTOS

        repo.save(pizzaForm);

        return "redirect:/pizze";
    }


}
