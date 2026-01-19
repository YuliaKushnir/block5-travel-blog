package org.example.block5travelblog.service;

import lombok.RequiredArgsConstructor;
import org.example.block5travelblog.data.MailData;
import org.example.block5travelblog.enums.MailStatus;
import org.example.block5travelblog.repository.MailRepository;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of {@link ScheduledSendingMessageService}.
 * Periodically retries sending mails with ERROR status.
 */
@EnableScheduling
@Service
@RequiredArgsConstructor
public class ScheduledSendingMessageServiceImpl implements ScheduledSendingMessageService {

    private final MailRepository mailRepository;
    private final MailServiceImpl mailService;

    /**
     * Retries sending mails with ERROR status every 5 minutes.
     * Uses {@link MailServiceImpl#attemptToSend(MailData)} for each failed mail.
     */
    @Override
    @Scheduled(fixedRate = 300000)
    public void retryFailedEmails() {
        List<MailData> failedMails = mailRepository.findByStatus(MailStatus.ERROR);
        failedMails.forEach(mailService::attemptToSend);
    }
}