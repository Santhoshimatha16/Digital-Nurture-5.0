package com.cognizant.ormlearn.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Hands-on 3: Question entity.
 * Table: question (qu_id, qu_text)
 *
 * A question has multiple options.
 */
@Entity
@Table(name = "question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "qu_id")
    private int id;

    @Column(name = "qu_text")
    private String text;

    // OneToMany - question has many options
    @OneToMany(mappedBy = "question")
    private List<QuizOption> optionList;

    public Question() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    public List<QuizOption> getOptionList() { return optionList; }
    public void setOptionList(List<QuizOption> optionList) { this.optionList = optionList; }

    @Override
    public String toString() {
        return "Question [id=" + id + ", text=" + text + "]";
    }
}
