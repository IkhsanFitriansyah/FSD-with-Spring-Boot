package id.metrodataacademy.clientapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import id.metrodataacademy.clientapp.models.Employee;

@Service
public class EmployeeService {
    
    @Value("${server.base.url}/employee")
    private String url;

    @Autowired
    private RestTemplate restTemplate;

    public Employee getById(Integer id) {  
        return restTemplate
        .exchange(url + "/" + id, 
         HttpMethod.GET, null, 
          Employee.class)
          .getBody();
    }

     public Employee update(Integer id, Employee employee) {  
        return restTemplate
        .exchange(url.concat("/" + id),
         HttpMethod.PUT, new HttpEntity<>(employee),
          Employee.class)
          .getBody();
    }
}
