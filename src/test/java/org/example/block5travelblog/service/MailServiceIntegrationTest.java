package org.example.block5travelblog.service;

import org.example.block5travelblog.config.TestElasticsearchConfiguration;
import org.example.block5travelblog.data.MailData;
import org.example.block5travelblog.enums.MailStatus;
import org.example.block5travelblog.messaging.EmailMessage;
import org.example.block5travelblog.repository.MailRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
@Import(TestElasticsearchConfiguration.class)
public class MailServiceIntegrationTest {

    @Autowired
    private MailServiceImpl mailService;

    @Autowired
    private MailRepository mailRepository;

    @MockBean
    private JavaMailSender mailSender;

    @BeforeEach
    void cleanUp() {
        mailRepository.deleteAll();
    }

    @Test
    public void shouldSendEmailSuccessfully_andSaveStatusSEND() {
        EmailMessage message = EmailMessage.builder()
                .subject("Test subject")
                .content("Hello world")
                .recipientsEmails(List.of("test@mail.com"))
                .build();

        doNothing().when(mailSender).send(any(SimpleMailMessage.class));

        mailService.processNewMail(message);

        List<MailData> mails = (List<MailData>) mailRepository.findAll();
        assertThat(mails).hasSize(1);

        MailData savedMail = mails.get(0);
        assertThat(savedMail.getStatus()).isEqualTo(MailStatus.SEND);
        assertThat(savedMail.getErrorMessage()).isNull();
        assertThat(savedMail.getAttemptCount()).isEqualTo(1);
        assertThat(savedMail.getLastAttempt()).isNotNull();
    }

    @Test
    public void shouldFailSendingEmail_andSaveStatusERROR() {
        EmailMessage message = EmailMessage.builder()
                .subject("Fail subject")
                .content("Boom")
                .recipientsEmails(List.of("fail@mail.com"))
                .build();
        doThrow(new MailSendException("SMTP error"))
                .when(mailSender)
                .send(any(SimpleMailMessage.class));

        mailService.processNewMail(message);

        List<MailData> mails = (List<MailData>) mailRepository.findAll();
        assertThat(mails).hasSize(1);

        MailData savedMail = mails.get(0);
        assertThat(savedMail.getStatus()).isEqualTo(MailStatus.ERROR);
        assertThat(savedMail.getAttemptCount()).isEqualTo(2); // було 1, стало +1
        assertThat(savedMail.getErrorMessage()).contains("MailSendException");
        assertThat(savedMail.getLastAttempt()).isNotNull();
    }
}