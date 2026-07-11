package com.cognizant.ormlearn.model;

import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Hands-on 3: Attempt entity.
 * Table: attempt (at_id, at_us_id, at_date)
 *
 * Represents a quiz attempt by a user.
 * An attempt has many attempt_questions.
 */
@Entity
@Table(name = "attempt")
public class Attempt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "at_id")
    private int id;

    @Temporal(TemporalType.DATE)
    @Column(name = "at_date")
    private Date date;

    // ManyToOne - attempt belongs to one user
    @ManyToOne
    @JoinColumn(name = "at_us_id")
    private User user;

    // OneToMany - attempt has many attempt_questions
    @OneToMany(mappedBy = "attempt")
    private List<AttemptQuestion> attemptQuestionList;

    public Attempt() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public List<AttemptQuestion> getAttemptQuestionList() { return attemptQuestionList; }
    public void setAttemptQuestionList(List<AttemptQuestion> attemptQuestionList) {
        this.attemptQuestionList = attemptQuestionList;
    }

    @Override
    public String toString() {
        return "Attempt [id=" + id + ", date=" + date + ", user=" + user + "]";
    }
}
