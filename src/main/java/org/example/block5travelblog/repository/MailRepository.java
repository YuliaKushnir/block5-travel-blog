package org.example.block5travelblog.repository;

import org.example.block5travelblog.data.MailData;
import org.example.block5travelblog.enums.MailStatus;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Repository interface for managing {@link MailData} entities in Elasticsearch.
 * Provides CRUD operations and custom queries for mail data.
 */
public interface MailRepository extends CrudRepository<org.example.block5travelblog.data.MailData, String> {
    List<MailData> findByStatus(MailStatus status);
}
