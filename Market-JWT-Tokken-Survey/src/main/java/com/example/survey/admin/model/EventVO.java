package com.example.survey.admin.model;

import java.util.Date;
//이벤트 참여
public class EventVO {
    private int seq;
    private String eventName; // 이벤트 제목
    private String question1; // 이벤트 게시물 질문
    private String question2; // 이벤트 게시물 질문
    private String question3; // 이벤트 게시물 질문
    private String question4; // 이벤트 게시물 질문
    private Date regDate; // 이벤트 생성날짜
    private Date expiredDate; // 이벤트 종료일자
    private String Category; // 카테고리

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
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

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public Date getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(Date expiredDate) {
        this.expiredDate = expiredDate;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }
}
