package id.metrodataacademy.serverapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import id.metrodataacademy.serverapp.models.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
    // Find emplyee by username
    @Query("SELECT e FROM Employee e WHERE e.user.username = :username")
    public Employee findByUsername(@Param("username") String username);
    // Find emplyee by name
    @Query("SELECT e FROM Employee e WHERE e.name LIKE %:name%")
    public List<Employee> findByName(@Param("name") String name);
}
