package com.example.survey.model;

import java.util.Date;

public class BoardVO {
    private int seq;
    private String content; //주관문항
    private String eventName;
    private String title;
    private Date lastUpdate;
    private int userSeq; //유저 시퀀스
    private String name;
    private int boardHit;
    private String question1; //질문
    private String question2;
    private String question3;
    private String question4;
    private String responsegradle1; //평점문항
    private String responsegradle2;
    private String responsegradle3;
    private int eventSeq; //이벤트 시퀀스

    private String email;
    private String userLastUpdate;
    private String id;
    private String tel;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserLastUpdate() {
        return userLastUpdate;
    }

    public void setUserLastUpdate(String userLastUpdate) {
        this.userLastUpdate = userLastUpdate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public int getEventSeq() {
        return eventSeq;
    }

    public void setEventSeq(int eventSeq) {
        this.eventSeq = eventSeq;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getQuestion4() {
        return question4;
    }

    public void setQuestion4(String question4) {
        this.question4 = question4;
    }

    public String getQuestion1() {
        return question1;
    }

    public void setQuestion1(String question1) {
        this.question1 = question1;
    }

    public String getQuestion2() {
        return question2;
    }

    public void setQuestion2(String question2) {
        this.question2 = question2;
    }

    public String getQuestion3() {
        return question3;
    }

    public void setQuestion3(String question3) {
        this.question3 = question3;
    }

    public String getResponsegradle1() {
        return responsegradle1;
    }

    public void setResponsegradle1(String responsegradle1) {
        this.responsegradle1 = responsegradle1;
    }

    public String getResponsegradle2() {
        return responsegradle2;
    }

    public void setResponsegradle2(String responsegradle2) {
        this.responsegradle2 = responsegradle2;
    }

    public String getResponsegradle3() {
        return responsegradle3;
    }

    public void setResponsegradle3(String responsegradle3) {
        this.responsegradle3 = responsegradle3;
    }

    public int getBoardHit() {
        return boardHit;
    }

    public void setBoardHit(int boardHit) {
        this.boardHit = boardHit;
    }

    public BoardVO() { }

    public BoardVO(int seq) {
        this.seq = seq;
    }

    public BoardVO(int seq, String title, String content) {
        this.seq = seq;
        this.title = title;
        this.content = content;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public int getUserSeq() {
        return userSeq;
    }

    public void setUserSeq(int userSeq) {
        this.userSeq = userSeq;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}