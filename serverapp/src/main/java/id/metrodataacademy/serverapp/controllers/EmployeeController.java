package id.metrodataacademy.serverapp.controllers;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import id.metrodataacademy.serverapp.models.Employee;
import id.metrodataacademy.serverapp.services.EmployeeService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/employee")
@PreAuthorize("hasAnyRole('ADMIN','USER')")
public class EmployeeController {
    
    private EmployeeService employeeService;

    // @PreAuthorize("hasAnyAuthority('read_user', 'read_admin')")
    @GetMapping
    public List<Employee> getAll() {
        return employeeService.getAll();
    }

    // @PreAuthorize("hasAuthority('read_admin')")
    @GetMapping("/{id}")
    public Employee getById(@PathVariable Integer id) {
        return employeeService.getById(id);
    }

    @PutMapping("/{id}")
    public Employee update(@PathVariable Integer id, @RequestBody Employee employee) {
        return employeeService.update(id, employee);
    }

    @DeleteMapping("/{id}")
    public Employee delete(@PathVariable Integer id) {
        return employeeService.delete(id);
    }

    // get Profile
    @GetMapping("/profile")
    public Employee getProfile(@RequestParam(name = "name") String username) {
        return employeeService.getByName(username);
    }
}
