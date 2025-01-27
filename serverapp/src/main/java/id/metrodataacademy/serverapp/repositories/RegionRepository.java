package id.metrodataacademy.serverapp.repositories;

import id.metrodataacademy.serverapp.models.Region;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionRepository extends JpaRepository<Region, Integer> {
    //Query Method
    public Optional<Region> findByName(String name);
}
