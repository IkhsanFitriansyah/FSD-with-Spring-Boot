package id.metrodataacademy.serverapp.services;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import id.metrodataacademy.serverapp.models.Region;
import id.metrodataacademy.serverapp.repositories.RegionRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RegionService {
    
    private RegionRepository regionRepository;

    public List<Region> getAll() {
        return regionRepository.findAll();
    }

    public Region getById(Integer id) {
        return regionRepository
        .findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Region Not Found!!!!"));
    }

    public Region create(Region region) {
        if (regionRepository.findByName(region.getName()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Name is Alredy Exists!");
        }
        return regionRepository.save(region);
    }

    public Region update(Integer id, Region region) {
        getById(id);
        region.setId(id);
        return regionRepository.save(region);
    }

    public Region delete(Integer id) {
        Region region = getById(id);
        regionRepository.delete(region);
        return region;
    }
}
