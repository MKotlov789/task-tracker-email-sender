package ru.mkotlov789.edu.pet.emailsender.dto;

import lombok.Data;

@Data
public class EmailDto {
    private String emailAdress;
    private String subject;
    private String body;
}
