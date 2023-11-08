package com.example.mlpnotificationservice.rabbitmq.events;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class SmsEvent extends Event implements Serializable {
    @JsonProperty
    private String countryCode;
    @JsonProperty
    private String phoneNumber;
    @JsonProperty
    private String message;
}
