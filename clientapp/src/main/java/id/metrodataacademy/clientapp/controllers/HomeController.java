package id.metrodataacademy.clientapp.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    
    @GetMapping
    public String home(Model model, Authentication auth) {
        // model.addAttribute("name", "SIBKM 05 Java_Ikhsan");
        model.addAttribute("name", auth.getName());
        model.addAttribute("isActive", "home");
        return "index";
    }

    @GetMapping("/home")
    public String dashboard(Model model, Authentication auth) {
        // model.addAttribute("name", "SIBKM 05 Java_Ikhsan");
        model.addAttribute("name", auth.getName());
        model.addAttribute("isActive", "home");
        return "index";
    }
}
