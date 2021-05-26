package com.example.survey.VO;

import com.example.survey.model.Membership;
import com.example.survey.model.Role;

public class User {
    private int seq = 0;
    private String email = "";
    private String name = "";
    private String phone = "";
    private String password = "";
    private String rePassword = "";
    private String regDate = "";
    private String lastDate = "";
    private boolean isLive = true;
    private String role = "";
    private String membership = Membership.FREE.getValue();

    private int trcUrlCount = 0; // 멤버쉽 등급에 따른 순위추적 링크 갯수
    private int trcUrlCurrentCount = 0; // 현재 추가된 순위추적 링크 갯수

    private String np = ""; // 비밀번호 변경시 사용, new-password
    private String cp = ""; // 비밀번호 변경시 사용, current-password

    public User() {
        this.role = Role.USER.getValue();
    }

    public User (String email, String name, String phone, String password, Membership membership) {
        this.role = Role.USER.getValue();
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.password = password;
        this.membership = Membership.FREE.getValue();
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRePassword() {
        return rePassword;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public String getLastDate() {
        return lastDate;
    }

    public void setLastDate(String lastDate) {
        this.lastDate = lastDate;
    }

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getMembership() {
        return membership;
    }

    public void setMembership(String membership) {
        this.membership = membership;
    }

    public int getTrcUrlCount() {
        return trcUrlCount;
    }

    public void setTrcUrlCount(int trcUrlCount) {
        this.trcUrlCount = trcUrlCount;
    }

    public int getTrcUrlCurrentCount() {
        return trcUrlCurrentCount;
    }

    public void setTrcUrlCurrentCount(int trcUrlCurrentCount) {
        this.trcUrlCurrentCount = trcUrlCurrentCount;
    }

    public String getNp() {
        return np;
    }

    public void setNp(String np) {
        this.np = np;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }
}
