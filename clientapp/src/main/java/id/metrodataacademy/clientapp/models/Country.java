package id.metrodataacademy.clientapp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Country {
    
    private Integer id;
    private String code;
    private String name;
    private Integer regionId;
    private Region region;
}
