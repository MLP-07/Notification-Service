package com.example.mlpnotificationservice.service.email;

import com.example.mlpnotificationservice.dto.NotificationResponseDto;
import com.example.mlpnotificationservice.model.Notification;
import com.example.mlpnotificationservice.model.NotificationType;
import com.example.mlpnotificationservice.repo.NotificationRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.mail.Address;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final NotificationRepository notificationRepository;
    private final ObjectMapper objectMapper;
    private final JavaMailSender mailSender;
    private final EmailTemplateFactory emailTemplateFactory;

    public void sendEmail(List<String> receipients, String subject, Map<String, Object> metaData, String userId) throws MessagingException {

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        mimeMessage.addFrom(new Address[]{new InternetAddress("bileba5065@jucatyo.com")});
        mimeMessage.setSubject(subject);

        mimeMessage.setRecipients(Message.RecipientType.TO, String.join(",", receipients));

        String htmlTemplate = readFile(emailTemplateFactory.getTemplateByType(TemplateType.NEW_GRIEVANCE));

        for (Map.Entry<String, Object> entry : metaData.entrySet()) {
            htmlTemplate = htmlTemplate.replace("${" + entry.getKey() + "}", entry.getValue().toString());
        }

        // Set the email's content to be the HTML template
        mimeMessage.setContent(htmlTemplate, "text/html; charset=utf-8");

        mailSender.send(mimeMessage);

        try {
            Map<String, Object> payloadMap = new HashMap<>(metaData);
            payloadMap.put("subject", subject);
            payloadMap.put("receipients", receipients);

            Notification notification = Notification.builder()
                    .userId(UUID.fromString(userId))
                    .notificationType(NotificationType.EMAIL)
                    .payload(objectMapper.writeValueAsString(payloadMap))
                    .build();
            notificationRepository.save(notification);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    String readFile(String fileName) {

        try {
            InputStream resource = new ClassPathResource("templates/" + fileName).getInputStream();

            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(resource))) {

                return reader.lines()
                        .collect(Collectors.joining("\n"));
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
