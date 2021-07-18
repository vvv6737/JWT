package com.example.demo;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class plustest {
    public void setId(int id) {
        this.id = id;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    int id ;
    String pw;
    int grade;

    public int getId() {
        return id;
    }

    public String getPw() {
        return pw;
    }

    public static void main(String[] args) {
        plustest memberid = new plustest();
        memberid.id= 123;
        memberid.pw = "dkdkdkdkk";
        System.out.println(memberid.getId());
    }
}
