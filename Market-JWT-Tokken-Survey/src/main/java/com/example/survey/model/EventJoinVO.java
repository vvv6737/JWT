package com.example.survey.model;

import java.util.Date;
//이벤트 참여자
public class EventJoinVO {
    private int seq;
    private String eventSeq; // 이벤트 테이블 seq
    private String userKey; // 사용자 식별값
    private String answer; // 응답값
    private Date regDate; // 참여 완료 시각

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public String getEventSeq() {
        return eventSeq;
    }

    public void setEventSeq(String eventSeq) {
        this.eventSeq = eventSeq;
    }

    public String getUserKey() {
        return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }
}