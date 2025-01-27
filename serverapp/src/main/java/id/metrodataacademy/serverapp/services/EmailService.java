package id.metrodataacademy.serverapp.services;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import id.metrodataacademy.serverapp.models.dto.request.EmailReq;
import id.metrodataacademy.serverapp.models.dto.response.EmailResponse;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmailService {
    
    private JavaMailSender mailSender;
    private Configuration config;

    public EmailReq sendSimpleMessage(EmailReq emailReq) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(emailReq.getTo());
        message.setSubject(emailReq.getSubject());
        message.setText(emailReq.getText());

        mailSender.send(message);

        return emailReq;
    }

    public EmailReq sendMessageWithAttach(EmailReq emailReq) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(emailReq.getTo());
            helper.setSubject(emailReq.getSubject());
            helper.setText(emailReq.getText());

            FileSystemResource file = new FileSystemResource(
                new File(emailReq.getAttachment())
            );
            helper.addAttachment(file.getFilename(), file);
            mailSender.send(message);
        } catch (Exception e) {
            System.out.println("Error = " + e.getMessage());
        }
        return emailReq;
    }

    //HTML Emails
    public EmailResponse sendEmailWithTemplate(EmailReq emailReq, Map<String, Object> model) {
        EmailResponse response = new EmailResponse();
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(
                message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
            FileSystemResource file = new FileSystemResource(
                new File(emailReq.getAttachment())
            );
            helper.addAttachment(file.getFilename(), file);

            Template t = config.getTemplate("email-template.ftl");
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);

            helper.setTo(emailReq.getTo());
            helper.setSubject(emailReq.getSubject());
            helper.setText(html, true);
            mailSender.send(message);

            response.setMessage("Mail send to : " + emailReq.getTo());
            response.setStatus("Send Mail Success");
        } catch (Exception e) {
            response.setMessage("Mail send to :" + e.getMessage());
            response.setStatus("Send Mail Failure");
        }

        return response;
    }


}
