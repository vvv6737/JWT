package com.example.survey.model;

import java.util.Date;
//설문 응답
public class PollResponseVO {
    private int userSeq;
    private int userPollSeq;
    private String responseContent;
    private Date responseDate;
    private String pointGive;
    private Date pointDate;

    public int getUserSeq() {
        return userSeq;
    }

    public void setUserSeq(int userSeq) {
        this.userSeq = userSeq;
    }

    public int getUserPollSeq() {
        return userPollSeq;
    }

    public void setUserPollSeq(int userPollSeq) {
        this.userPollSeq = userPollSeq;
    }

    public String getResponseContent() {
        return responseContent;
    }

    public void setResponseContent(String responseContent) {
        this.responseContent = responseContent;
    }

    public Date getResponseDate() {
        return responseDate;
    }

    public void setResponseDate(Date responseDate) {
        this.responseDate = responseDate;
    }

    public String getPointGive() {
        return pointGive;
    }

    public void setPointGive(String pointGive) {
        this.pointGive = pointGive;
    }

    public Date getPointDate() {
        return pointDate;
    }

    public void setPointDate(Date pointDate) {
        this.pointDate = pointDate;
    }
}