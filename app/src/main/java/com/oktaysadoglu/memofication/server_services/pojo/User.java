package com.oktaysadoglu.memofication.server_services.pojo;

import java.util.List;

/**
 * Created by oktaysadoglu on 23/02/2017.
 */

public class User {

    private String id;

    private List<AnsweredWord> answeredWords;

    public User(String id, List<AnsweredWord> answeredWords) {
        this.id = id;
        this.answeredWords = answeredWords;
    }

    public List<AnsweredWord> getAnsweredWords() {
        return answeredWords;
    }

    public void setAnsweredWords(List<AnsweredWord> answeredWords) {
        this.answeredWords = answeredWords;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
