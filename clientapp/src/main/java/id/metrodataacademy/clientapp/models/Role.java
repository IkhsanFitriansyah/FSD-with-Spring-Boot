package id.metrodataacademy.clientapp.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    
    private Integer id;
    private String name;
    private List<User> users;
    private List<Privilege> privileges;
}
