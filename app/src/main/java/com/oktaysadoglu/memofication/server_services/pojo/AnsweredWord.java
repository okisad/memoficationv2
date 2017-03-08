package com.oktaysadoglu.memofication.server_services.pojo;

/**
 * Created by oktaysadoglu on 23/02/2017.
 */

public class AnsweredWord {

    private String id;
    private int answer;
    private int seeNumber;

    public int getSeeNumber() {
        return seeNumber;
    }

    public void setSeeNumber(int seeNumber) {
        this.seeNumber = seeNumber;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
