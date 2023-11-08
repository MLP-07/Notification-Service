package com.example.mlpnotificationservice.rabbitmq;

import com.example.mlpnotificationservice.rabbitmq.events.EmailEvent;
import com.example.mlpnotificationservice.service.email.EmailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailEventReceiver {

    private final EmailService emailService;

    @RabbitListener(queues = "email-queue")
    public void receiveMessage(EmailEvent event) {

        try {
            emailService.sendEmail(event.getRecipients(), event.getSubject(), event.getMetaData(), event.getUserId());
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
