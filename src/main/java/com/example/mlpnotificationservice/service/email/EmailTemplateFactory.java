package com.example.mlpnotificationservice.service.email;

public class EmailTemplateFactory {

    String getTemplateByType(TemplateType type) {

        return "new_grievance.html";
    }
}
