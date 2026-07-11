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
 * Hands-on 3: QuizOption entity.
 * Table: options (op_id, op_text, op_score, op_qu_id)
 *
 * Each option belongs to one question and carries a score.
 * Named QuizOption (not Option) to avoid conflict with java.util.Optional / SQL reserved word.
 */
@Entity
@Table(name = "options")
public class QuizOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "op_id")
    private int id;

    @Column(name = "op_text")
    private String text;

    @Column(name = "op_score")
    private double score;

    // ManyToOne - option belongs to one question
    @ManyToOne
    @JoinColumn(name = "op_qu_id")
    private Question question;

    public QuizOption() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    public double getScore() { return score; }
    public void setScore(double score) { this.score = score; }

    public Question getQuestion() { return question; }
    public void setQuestion(Question question) { this.question = question; }

    @Override
    public String toString() {
        return "QuizOption [id=" + id + ", text=" + text + ", score=" + score + "]";
    }
}
