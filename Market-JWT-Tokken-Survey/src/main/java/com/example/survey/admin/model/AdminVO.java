package com.example.survey.admin.model;

public class AdminVO {
    private int adminSeq;
    private String managerId;
    private String managerPw;
    private String managerName;

    public int getAdminSeq() {
        return adminSeq;
    }

    public void setAdminSeq(int adminSeq) {
        this.adminSeq = adminSeq;
    }

    public AdminVO () {}

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public String getManagerPw() {
        return managerPw;
    }

    public void setManagerPw(String managerPw) {
        this.managerPw = managerPw;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    @Override
    public String toString() {
        return "AdminVO{" +
                "managerId='" + managerId + '\'' +
                ", managerPw='" + managerPw + '\'' +
                ", managerName='" + managerName + '\'' +
                '}';
    }
}
