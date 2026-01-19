package org.example.block5travelblog.repository;

import org.example.block5travelblog.Block5TravelBlogApplication;
//import org.example.block5travelblog.config.TestElasticsearchConfiguration;
import org.example.block5travelblog.data.MailData;
import org.example.block5travelblog.enums.MailStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.test.context.ContextConfiguration;

import java.time.Instant;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
//@ContextConfiguration(classes = {Block5TravelBlogApplication.class, TestElasticsearchConfiguration.class})
class MailRepositoryTest {

//    @Autowired
//    private ElasticsearchOperations elasticsearchOperations;

//    @Autowired
//    private MailRepository mailRepository;

//    @Test
//    void testSaveAndFindByStatus() {
//        MailData mail = MailData.builder()
//                .subject("Test subject")
//                .content("Test content")
//                .recipientsEmails(List.of("test@example.com"))
//                .status(MailStatus.NEW)
//                .attemptCount(1)
//                .lastAttempt(Instant.now())
//                .build();
//
//        MailData saved = elasticsearchOperations.save(mail);
//
//        MailData found = elasticsearchOperations.get(saved.getId());
//
//        assertThat(found).isNotEmpty();
//        assertThat(found.get(0).getSubject()).isEqualTo("Test subject");
//    }

}