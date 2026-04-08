package com.example.AdministratorBussiness.servicio;

import com.example.AdministratorBussiness.dto.email.EmailDto;
import com.example.AdministratorBussiness.repositorio.EmailRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class EmailServicio implements EmailRepository {

    private final JavaMailSender javaMailSender;

    private final TemplateEngine templateEngine;

    public EmailServicio(JavaMailSender javaMailSender, TemplateEngine templateEngine) {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
    }

    @Override
    public void enviarCorreo(EmailDto emailDto) throws MessagingException {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setTo(emailDto.getDestinatario());
            helper.setSubject(emailDto.getAsunto());

            Context context = new Context();
            context.setVariable("mensaje", emailDto.getMensaje());
            String contenidoHtml = templateEngine.process("email", context);
            helper.setText(contenidoHtml, true);
            javaMailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
