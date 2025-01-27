package id.metrodataacademy.serverapp.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import id.metrodataacademy.serverapp.models.Employee;
import id.metrodataacademy.serverapp.models.Role;
import id.metrodataacademy.serverapp.models.User;
import id.metrodataacademy.serverapp.models.dto.request.EmployeeReq;
import id.metrodataacademy.serverapp.models.dto.request.LoginReq;
import id.metrodataacademy.serverapp.models.dto.response.LoginResponse;
import id.metrodataacademy.serverapp.repositories.EmployeeRepository;
import id.metrodataacademy.serverapp.repositories.UserRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthService {

  private EmployeeRepository employeeRepository;
  private ModelMapper modelMapper;
  private RoleService roleService;
  private PasswordEncoder passwordEncoder;
  private AuthenticationManager authenticationManager;
  private UserRepository userRepository;
  private AppUserDetailService appUserDetailService;

  public Employee registration(EmployeeReq employeeReq) {
    Employee employee = modelMapper.map(employeeReq, Employee.class);
    User user = modelMapper.map(employeeReq, User.class);

    user.setPassword(passwordEncoder.encode(employeeReq.getPassword()));

    List<Role> roles = new ArrayList<>();
    roles.add(roleService.getById(2));
    user.setRoles(roles);
    
    employee.setUser(user);
    user.setEmployee(employee);

    return employeeRepository.save(employee);
  }

  public LoginResponse login(LoginReq loginReq) {

    UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(
      loginReq.getUsername(),
      loginReq.getPassword()
    );

    Authentication auth = authenticationManager.authenticate(authReq);
    SecurityContextHolder.getContext().setAuthentication(auth);

    User user = userRepository
      .findByUsernameOrEmployeeEmail(
        loginReq.getUsername(),
        loginReq.getUsername()
      )
      .get();

    UserDetails userDetails = appUserDetailService.loadUserByUsername(
      loginReq.getUsername()
    );

    LoginResponse loginResponse = new LoginResponse();
    loginResponse.setUsername(user.getUsername());
    loginResponse.setEmail(user.getEmployee().getEmail());
    loginResponse.setAuthorities(
      userDetails
        .getAuthorities()
        .stream()
        .map(authority -> authority.getAuthority())
        .collect(Collectors.toList())
    );

    return loginResponse;
  }
}
