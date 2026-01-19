package org.example.block5travelblog.listeners;


//import org.example.block5travelblog.messaging.EmailMessage;
//import org.example.block5travelblog.service.MailServiceImpl;
//import org.junit.jupiter.api.Test;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.SpyBean;
//import org.springframework.test.annotation.DirtiesContext;
//import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.mockito.Mockito.after;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;

//@SpringBootTest
//@DirtiesContext
//@ActiveProfiles("test")
class MailListenerTest {

//    @Autowired
//    private RabbitTemplate rabbitTemplate;
//
//    @SpyBean
//    private MailServiceImpl mailService;
//
//    @Test
//    void testFullCycleMessageSendAndReceive() {
//        EmailMessage message = EmailMessage.builder()
//                .subject("Integration Test Subject")
//                .content("Integration Test Content")
//                .recipientsEmails(List.of("test@example.com"))
//                .build();
//
//        // напряму відправляємо у RabbitMQ
//        rabbitTemplate.convertAndSend("travel-blog", "post.created", message);
//
//        // перевіряємо, що MailServiceImpl.processNewMail викликано слухачем
//        verify(mailService, after(5000)).processNewMail(any(EmailMessage.class));
//    }

}