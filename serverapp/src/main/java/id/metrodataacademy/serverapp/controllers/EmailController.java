package id.metrodataacademy.serverapp.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.metrodataacademy.serverapp.models.dto.request.EmailReq;
import id.metrodataacademy.serverapp.models.dto.response.EmailResponse;
import id.metrodataacademy.serverapp.services.EmailService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/email")
public class EmailController {
    
    private EmailService emailService;

    @PostMapping("/simple")
    public EmailReq sendSimpleMessage(@RequestBody EmailReq emailReq) {
        return emailService.sendSimpleMessage(emailReq);
    }

    @PostMapping("/attach")
    public EmailReq sendMessageWithAttach(@RequestBody EmailReq emailReq) {
        return emailService.sendMessageWithAttach(emailReq);
    }

    @PostMapping("/template")
    public EmailResponse sendMassageWithTemplate(@RequestBody EmailReq emailReq) {
        Map<String,Object> model = new HashMap<>();
        return emailService.sendEmailWithTemplate(emailReq, model);
    }
}
