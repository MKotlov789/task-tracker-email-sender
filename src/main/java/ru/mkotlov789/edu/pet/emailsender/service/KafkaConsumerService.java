package ru.mkotlov789.edu.pet.emailsender.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;
import ru.mkotlov789.edu.pet.emailsender.dto.EmailDto;
@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaConsumerService {
    @Autowired
    private final EmailService emailService;
    private static final String topic = "${topic}";
    private static final String kafkaConsumerGroupId = "${spring.kafka.consumer.group-id}";

    @KafkaListener(topics = topic,
            groupId = kafkaConsumerGroupId,
            properties = {"spring.json.value.default.type=ru.mkotlov789.edu.pet.emailsender.dto.EmailDto"})
    public void sendEmail(EmailDto emailDto) {
        log.info("Request to send email received: "+emailDto);

        try {
            emailService.sendEmail(
                    emailDto.getEmailAdress(),
                    emailDto.getSubject(),
                    emailDto.getBody()
            );
        } catch (MailException e) {
            log.error("Could not send e-mail", e);
        }
    }
}
