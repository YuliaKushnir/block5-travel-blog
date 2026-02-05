package org.example.block5travelblog.service;

import lombok.RequiredArgsConstructor;
import org.example.block5travelblog.data.MailData;
import org.example.block5travelblog.enums.MailStatus;
import org.example.block5travelblog.repository.MailRepository;
import org.springframework.beans.factory.annotation.Value;
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
    private final MailService mailService;


    @Value("${mail.retry.fixedRate}")
    private long fixedRateRetry;

    /**
     * Retries sending mails with ERROR status every 5 minutes.
     * Uses {@link MailServiceImpl#attemptToSend(MailData)} for each failed mail.
     */
    @Override
    @Scheduled(fixedRateString = "${mail.retry.fixedRate}")
    public void retryFailedEmails() {
        List<MailData> failedMails = mailRepository.findByStatus(MailStatus.ERROR);
        failedMails.forEach(mailService::attemptToSend);
    }
}