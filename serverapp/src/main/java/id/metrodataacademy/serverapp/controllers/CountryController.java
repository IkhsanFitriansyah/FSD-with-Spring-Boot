package id.metrodataacademy.serverapp.controllers;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.metrodataacademy.serverapp.models.Country;
import id.metrodataacademy.serverapp.models.dto.request.CountryReq;
import id.metrodataacademy.serverapp.services.CountryService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/country")
@PreAuthorize("hasRole('ADMIN')")
public class CountryController {
    
    private CountryService countryService;

    @GetMapping
    public List<Country> getAll() {
        return countryService.getAll();
    }

    @GetMapping("/{id}")
    public Country getById(@PathVariable Integer id) {
        return countryService.getById(id);
    }

    @PostMapping
    public Country create(@RequestBody Country country) {
        return countryService.create(country);
    }

    @PostMapping ("/dto-mm")
    public Country createDTOByMM(@RequestBody CountryReq countryReq) {
        return countryService.createDTOByMM(countryReq);
    }

    @PutMapping("/{id}")
    public Country update(@PathVariable Integer id, @RequestBody Country country) {
        return countryService.update(id, country);
    }

    @DeleteMapping("/{id}")
    public Country deleteCountry(@PathVariable Integer id) {
        return countryService.delete(id);
    }
}
