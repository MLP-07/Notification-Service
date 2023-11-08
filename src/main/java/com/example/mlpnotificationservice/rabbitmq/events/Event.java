package com.example.mlpnotificationservice.rabbitmq.events;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Event {

    @JsonProperty
    private String userId;
}
