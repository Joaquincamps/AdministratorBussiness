package com.example.AdministratorBussiness.repositorio;

import com.example.AdministratorBussiness.dto.email.EmailDto;
import jakarta.mail.MessagingException;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository {

    public void enviarCorreo(EmailDto emailDto) throws MessagingException;
}
