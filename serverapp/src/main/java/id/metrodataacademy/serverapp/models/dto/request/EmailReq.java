package id.metrodataacademy.serverapp.models.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailReq {
    
    private String to;
    private String subject;
    private String text;
    private String attachment;
}
