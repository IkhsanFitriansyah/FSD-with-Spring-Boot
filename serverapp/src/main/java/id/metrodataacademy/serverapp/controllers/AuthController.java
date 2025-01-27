package id.metrodataacademy.serverapp.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.metrodataacademy.serverapp.models.Employee;
import id.metrodataacademy.serverapp.models.dto.request.EmployeeReq;
import id.metrodataacademy.serverapp.models.dto.request.LoginReq;
import id.metrodataacademy.serverapp.models.dto.response.LoginResponse;
import id.metrodataacademy.serverapp.services.AuthService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping
public class AuthController {

  private AuthService authService;

  @PostMapping("/registration")
  public Employee registration(@RequestBody EmployeeReq employeeReq) {
    return authService.registration(employeeReq);
  }

  @PostMapping("/login")
  public LoginResponse login(@RequestBody LoginReq loginReq) {
    return authService.login(loginReq);
  }
}
