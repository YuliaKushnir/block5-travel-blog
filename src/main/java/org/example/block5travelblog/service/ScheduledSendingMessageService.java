package org.example.block5travelblog.service;

/**
 * Service interface for scheduled retry operations.
 */
public interface ScheduledSendingMessageService {
    void retryFailedEmails();
}
