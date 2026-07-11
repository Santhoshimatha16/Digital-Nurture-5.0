package com.cognizant.ormlearn.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Hands-on 3: AttemptOption entity.
 * Table: attempt_option (ao_id, ao_aq_id, ao_op_id, ao_is_answer)
 *
 * Represents which option was selected (ao_is_answer=true) for each
 * question in an attempt. All options for a question are listed, with
 * ao_is_answer marking the one the user selected.
 */
@Entity
@Table(name = "attempt_option")
public class AttemptOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ao_id")
    private int id;

    @Column(name = "ao_is_answer")
    private boolean isAnswer;

    // ManyToOne - attempt_option belongs to one attempt_question
    @ManyToOne
    @JoinColumn(name = "ao_aq_id")
    private AttemptQuestion attemptQuestion;

    // ManyToOne - attempt_option references one quiz option
    @ManyToOne
    @JoinColumn(name = "ao_op_id")
    private QuizOption quizOption;

    public AttemptOption() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public boolean isAnswer() { return isAnswer; }
    public void setAnswer(boolean isAnswer) { this.isAnswer = isAnswer; }

    public AttemptQuestion getAttemptQuestion() { return attemptQuestion; }
    public void setAttemptQuestion(AttemptQuestion attemptQuestion) {
        this.attemptQuestion = attemptQuestion;
    }

    public QuizOption getQuizOption() { return quizOption; }
    public void setQuizOption(QuizOption quizOption) { this.quizOption = quizOption; }

    @Override
    public String toString() {
        return "AttemptOption [id=" + id + ", isAnswer=" + isAnswer
                + ", option=" + quizOption + "]";
    }
}
