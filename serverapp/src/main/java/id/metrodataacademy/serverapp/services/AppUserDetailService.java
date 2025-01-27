package id.metrodataacademy.serverapp.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import id.metrodataacademy.serverapp.models.AppUserDetail;
import id.metrodataacademy.serverapp.models.User;
import id.metrodataacademy.serverapp.repositories.UserRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AppUserDetailService implements UserDetailsService{
    
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username)
    throws UsernameNotFoundException {
    User user = userRepository
      .findByUsernameOrEmployeeEmail(username, username)
      .orElseThrow(() -> new UsernameNotFoundException("Username or Email not found !!"));

    return new AppUserDetail(user);
  }
}
