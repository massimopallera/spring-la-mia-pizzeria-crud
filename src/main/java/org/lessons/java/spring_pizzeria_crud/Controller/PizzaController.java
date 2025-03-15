package org.lessons.java.spring_pizzeria_crud.Controller;

import java.util.List;

import org.lessons.java.spring_pizzeria_crud.Model.Pizza;
import org.lessons.java.spring_pizzeria_crud.Repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequestMapping("/")
public class PizzaController {
    
    @Autowired
    private PizzaRepository repo;
    
    @GetMapping
    public String index(Model model) {


        List<Pizza> pizze = repo.findAll();
        model.addAttribute("pizze", pizze);

        return "index";
    }
    
}
