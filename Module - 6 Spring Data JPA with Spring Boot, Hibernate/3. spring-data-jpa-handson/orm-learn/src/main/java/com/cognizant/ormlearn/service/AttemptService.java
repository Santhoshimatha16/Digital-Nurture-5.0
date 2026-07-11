package com.cognizant.ormlearn.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.ormlearn.model.Attempt;
import com.cognizant.ormlearn.repository.AttemptRepository;

/**
 * Hands-on 3: Service for quiz Attempt operations.
 */
@Service
public class AttemptService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AttemptService.class);

    @Autowired
    private AttemptRepository attemptRepository;

    /**
     * Retrieves a quiz attempt with all nested details (questions, options)
     * for the given user and attempt ids.
     *
     * @param userId    the user's id
     * @param attemptId the attempt id
     * @return fully-populated Attempt entity
     */
    @Transactional
    public Attempt getAttempt(int userId, int attemptId) {
        LOGGER.info("Start getAttempt userId={} attemptId={}", userId, attemptId);
        Attempt attempt = attemptRepository.getAttempt(userId, attemptId);
        LOGGER.info("End getAttempt");
        return attempt;
    }
}
