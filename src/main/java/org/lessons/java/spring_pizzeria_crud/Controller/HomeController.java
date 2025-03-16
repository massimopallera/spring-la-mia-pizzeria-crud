package org.lessons.java.spring_pizzeria_crud.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/")
public class HomeController {
 
    
    @GetMapping
    public String index() {
        return "pizze/index";
    }
    

}
