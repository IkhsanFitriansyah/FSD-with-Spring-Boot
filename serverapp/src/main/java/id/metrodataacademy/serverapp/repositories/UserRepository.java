package id.metrodataacademy.serverapp.repositories;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import id.metrodataacademy.serverapp.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
    Optional<User> findByUsernameOrEmployeeEmail(String username, String email);
}
