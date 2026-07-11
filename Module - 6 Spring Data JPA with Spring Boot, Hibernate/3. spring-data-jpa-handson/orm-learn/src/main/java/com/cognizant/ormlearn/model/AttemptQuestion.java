package com.cognizant.ormlearn.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Hands-on 3: AttemptQuestion entity.
 * Table: attempt_question (aq_id, aq_at_id, aq_qu_id)
 *
 * Represents which questions were part of a given attempt.
 * One attempt_question has many attempt_options.
 */
@Entity
@Table(name = "attempt_question")
public class AttemptQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "aq_id")
    private int id;

    // ManyToOne - attempt_question belongs to one attempt
    @ManyToOne
    @JoinColumn(name = "aq_at_id")
    private Attempt attempt;

    // ManyToOne - attempt_question references one question
    @ManyToOne
    @JoinColumn(name = "aq_qu_id")
    private Question question;

    // OneToMany - attempt_question has many attempt_options
    @OneToMany(mappedBy = "attemptQuestion")
    private List<AttemptOption> attemptOptionList;

    public AttemptQuestion() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Attempt getAttempt() { return attempt; }
    public void setAttempt(Attempt attempt) { this.attempt = attempt; }

    public Question getQuestion() { return question; }
    public void setQuestion(Question question) { this.question = question; }

    public List<AttemptOption> getAttemptOptionList() { return attemptOptionList; }
    public void setAttemptOptionList(List<AttemptOption> attemptOptionList) {
        this.attemptOptionList = attemptOptionList;
    }

    @Override
    public String toString() {
        return "AttemptQuestion [id=" + id + ", question=" + question + "]";
    }
}
