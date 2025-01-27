package id.metrodataacademy.serverapp.models.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountryReq {
    
    private String code;
    private String name;
    private Integer regionId;
}
