package id.metrodataacademy.clientapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import id.metrodataacademy.clientapp.models.Region;
import id.metrodataacademy.clientapp.services.RegionService;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/region")
public class RegionController {
    
    private RegionService regionService;

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("regions", regionService.getAll());
        model.addAttribute("isActive", "region");
        return "region/index";
    }
    
    @GetMapping("/create")
    public String createView(Region region) {
        return "region/create-view";
    }

    @PostMapping
    public String create(Region region) {
        regionService.create(region);
        return "redirect:/region";
    }

    // GetById (Regions)
    @GetMapping("/{id}")
    public String detailView(@PathVariable Integer id, Model model) {
        model.addAttribute("region", regionService.getById(id));
        model.addAttribute("isActive", "region");
        return "region/detail-view";
    }

    // Get Id to Update
    @GetMapping("/update/{id}")
    public String updateView(@PathVariable Integer id, Model model, Region region) {
        model.addAttribute("region", regionService.getById(id));
        model.addAttribute("isActive", "region");
        return "region/update-view";
    }

    // Updated
    @PutMapping("/{id}")
    public String update(@PathVariable Integer id, Region region) {
        regionService.update(id, region);
        return "redirect:/region";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id) {
        regionService.delete(id);
        return "redirect:/region";
    }
    
}
