package id.metrodataacademy.serverapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import id.metrodataacademy.serverapp.models.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {
    
}
