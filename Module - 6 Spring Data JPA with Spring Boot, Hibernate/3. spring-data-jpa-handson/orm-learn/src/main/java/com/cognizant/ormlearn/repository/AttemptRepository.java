package com.cognizant.ormlearn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cognizant.ormlearn.model.Attempt;

/**
 * Hands-on 3: AttemptRepository
 *
 * HQL join order (as required by the hands-on):
 *   user -> attempt -> attempt_question -> question -> attempt_option -> options
 *
 * 'fetch' is used on all OneToMany / ManyToMany associations to populate the
 * bean in a single query and avoid LazyInitializationException.
 *
 * ManyToOne associations (e.g., attempt.user, attemptQuestion.question)
 * do NOT need fetch here since they are already single-row lookups,
 * but are fetched anyway for completeness.
 */
@Repository
public interface AttemptRepository extends JpaRepository<Attempt, Integer> {

    /**
     * HQL: Retrieve a specific attempt (by userId and attemptId) with all
     * nested details fetched in a single query.
     *
     * Join path:
     *   Attempt (at) -> User (u)                              [ManyToOne]
     *               -> AttemptQuestion (aq)  [OneToMany - fetch]
     *                    -> Question (q)                      [ManyToOne]
     *                    -> AttemptOption (ao) [OneToMany - fetch]
     *                         -> QuizOption (op)              [ManyToOne]
     *
     * @param userId    the user's id
     * @param attemptId the attempt's id
     * @return the Attempt entity with all data populated
     */
    @Query("SELECT at FROM Attempt at "
            + "join fetch at.user u "
            + "join fetch at.attemptQuestionList aq "
            + "join fetch aq.question q "
            + "join fetch aq.attemptOptionList ao "
            + "join fetch ao.quizOption op "
            + "WHERE u.id = :userId AND at.id = :attemptId")
    Attempt getAttempt(@Param("userId") int userId, @Param("attemptId") int attemptId);
}
