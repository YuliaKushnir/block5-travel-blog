package org.example.block5travelblog.listeners;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.block5travelblog.messaging.EmailMessage;
import org.example.block5travelblog.service.MailServiceImpl;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * RabbitMQ listener for incoming email messages.
 * Listens to the "create-post-queue" and delegates processing to MailServiceImpl.
 */
@EnableRabbit
@Component
@RequiredArgsConstructor
@Slf4j
public class MailListener {

    private final MailServiceImpl mailServiceImpl;

    /**
     * Handles messages received from RabbitMQ queue.
     *
     * @param emailMessage the incoming email message
     */
    @RabbitListener(queues = "create-post-queue")
    public void handleMessage(EmailMessage emailMessage) {
        log.info("Received message from queue: {}", emailMessage);
        try {
            mailServiceImpl.processNewMail(emailMessage);
            log.info("Message processed successfully: {}", emailMessage.getSubject());
        } catch (Exception ex) {
            log.warn("Error while processing message {}: {}", emailMessage.getSubject(), ex.getMessage(), ex);
        }
    }
}