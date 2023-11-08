package com.example.mlpnotificationservice.rabbitmq.events;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class EmailEvent extends Event implements Serializable {
    @JsonProperty
    List<String> recipients;
    @JsonProperty
    String body;
    @JsonProperty
    String subject;
    @JsonProperty
    Map<String, Object> metaData;
    @JsonProperty
    String purpose;
}
