package id.metrodataacademy.serverapp.models.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeReq {

    private String name;
    private String email;
    private String phone;
    private String username;
    private String password;
}
