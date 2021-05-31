package com.example.survey.model;

import java.util.Date;

public class ReplyVO {
    private int reseq; //댓글 번호
    private int reuserseq; // 댓글 작성자 시퀀스
    private int seq; //게시글 번호
    private String name; //댓글 작성자 이름
    private Date lastUpdate; //작성날짜
    private String recontent; //댓글 내용
    private int eventSeq;

    public int getEventSeq() {
        return eventSeq;
    }

    public void setEventSeq(int eventSeq) {
        this.eventSeq = eventSeq;
    }

    public int getReuserseq() {
        return reuserseq;
    }

    public void setReuserseq(int reuserseq) {
        this.reuserseq = reuserseq;
    }

    public String getName() {
        return name;
    }

    public int getReseq() {
        return reseq;
    }

    public void setReseq(int reseq) {
        this.reseq = reseq;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public String getName(String name, String sampleVOName) {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getRecontent() {
        return recontent;
    }

    public void setRecontent(String recontent) {
        this.recontent = recontent;
    }
}
