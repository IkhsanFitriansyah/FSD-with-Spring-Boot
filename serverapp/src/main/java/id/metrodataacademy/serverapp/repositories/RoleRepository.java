package id.metrodataacademy.serverapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import id.metrodataacademy.serverapp.models.Role;

@Repository
public interface RoleRepository extends JpaRepository <Role, Integer> {
    
}
