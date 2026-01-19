package org.example.block5travelblog.service;

import org.example.block5travelblog.data.MailData;
import org.example.block5travelblog.messaging.EmailMessage;

/**
 * Service interface for mail operations.
 */
public interface MailService {
    void processNewMail(EmailMessage emailMessage);
    void attemptToSend(MailData mailData);
}