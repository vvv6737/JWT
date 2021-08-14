package com.example.survey.config;

import java.io.File;

public class fileTest {
    public static void main(String[] args) {
        String absolutePath = "/src/main/resources/static/upload/";
        File file = new File(System.getProperty("user.dir") + absolutePath);

        if (file.exists()) {
            if (file.delete()) {
                System.out.println(file);
                System.out.println("파일삭제 성공");
            } else {
                System.out.println(file);
                System.out.println("파일삭제 실패");
            }
        } else {
            System.out.println(file);
            System.out.println("파일이 존재하지 않습니다.");
        }
    }
}
