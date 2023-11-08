package com.example.mlpnotificationservice.rabbitmq;

import com.example.mlpnotificationservice.rabbitmq.events.SmsEvent;
import com.example.mlpnotificationservice.service.SmsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class SmsEventReceiver {

    private final SmsService smsService;

    @RabbitListener(queues = "sms-queue")
    public void receiveMessage(SmsEvent event) {
        smsService.sendSms(event.getCountryCode(), event.getPhoneNumber(), event.getMessage(), event.getUserId());
    }
}
