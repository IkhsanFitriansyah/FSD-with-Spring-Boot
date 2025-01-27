package id.metrodataacademy.clientapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import id.metrodataacademy.clientapp.models.Country;

@Service
public class CountryService {
    
    @Value("${server.base.url}/country")
    private String url;

    @Autowired
    private RestTemplate restTemplate;

    // Get All (Countries)
    public List<Country> getAll() {
        return restTemplate
        .exchange(url, 
        HttpMethod.GET, 
        null, 
        new ParameterizedTypeReference<List<Country>>() {
        })
           .getBody();
    }

    // GetById (Countries)
    public Country getById(Integer id) {
        return restTemplate
        .exchange(url + "/" + id, 
        HttpMethod.GET, 
        null, 
        Country.class)
        .getBody();
    }

    // Create (Country)
    public Country create(Country country) {
        return restTemplate
        .exchange(url,HttpMethod.POST, 
        new HttpEntity<>(country),
        Country.class)
        .getBody();
    }

    // Update (Country)
    public Country Update(Integer id, Country country) {
        return restTemplate
        .exchange(url.concat("/" + id), HttpMethod.PUT, 
        new HttpEntity<>(country),
        Country.class)
        .getBody();
    }

    // Delete (Country)
    public Country delete(Integer id) {
        return restTemplate
        .exchange(url.concat("/" + id), 
        HttpMethod.DELETE, 
        null, 
        Country.class, id)
        .getBody();
    }
}
