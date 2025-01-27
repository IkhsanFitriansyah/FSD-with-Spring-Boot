package id.metrodataacademy.clientapp.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import id.metrodataacademy.clientapp.models.Country;
import id.metrodataacademy.clientapp.services.CountryService;
import id.metrodataacademy.clientapp.services.RegionService;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/country")
public class CountryContoller {
    
    private CountryService countryService;
    private RegionService regionService;

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("countries", countryService.getAll());
        model.addAttribute("isActive", "country");
        return "country/index";
    }

    @GetMapping("/{id}")
    public String getById(@PathVariable Integer id, Model model) {
        model.addAttribute("country", countryService.getById(id));
        model.addAttribute("isActive", "country");
        return "country/detail-view";
    }

    @GetMapping("/create")
    public String createView(Country country, Model model) {
        model.addAttribute("regions", regionService.getAll());
        model.addAttribute("isActive", "country");
        return "country/create-view";
    }

    @PostMapping("/CreateSaved")
    public String create(Country country) {
        countryService.create(country);
        return "redirect:/country";
    }

    @GetMapping("/update/{id}")
    public String updateView(@PathVariable("id") Integer id, Country country, Model model) {
        model.addAttribute("country", countryService.getById(id));
        model.addAttribute("regions", regionService.getAll());
        model.addAttribute("isActive", "country");
        return "country/update-view";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Integer id, Country country) {
        countryService.Update(id, country);
        return "redirect:/country";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id) {
        countryService.delete(id);
        return "redirect:/country";
    }
}
