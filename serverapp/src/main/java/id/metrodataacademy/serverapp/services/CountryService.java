package id.metrodataacademy.serverapp.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import id.metrodataacademy.serverapp.models.Country;
import id.metrodataacademy.serverapp.models.dto.request.CountryReq;
import id.metrodataacademy.serverapp.repositories.CountryRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CountryService {
    
    private CountryRepository countryRepository;
    private RegionService regionService;
    private ModelMapper modelMapper;

    public List<Country> getAll() {
        return countryRepository.findAll();
    }
    
    public Country getById(Integer id) {
        return countryRepository
        .findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Country Not Found!!!"));
    }

    public Country create(Country country) {
        return countryRepository.save(country);
    }

    //DTO with Model Mapper
    public Country createDTOByMM(CountryReq countryReq){
        Country country = modelMapper.map(countryReq, Country.class);
        country.setRegion(regionService.getById(countryReq.getRegionId()));
        return countryRepository.save(country);
    }

    public Country update(Integer id, Country country) {
        getById(id);
        country.setId(id);
        return countryRepository.save(country);
    }

    public Country delete(Integer id) {
        Country country = getById(id);
        countryRepository.delete(country);
        return country;
    }

}
