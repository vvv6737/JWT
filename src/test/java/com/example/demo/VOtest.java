package com.example.demo;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class VOtest {
    public static void main(String[] args) {

        GradeVO grade = new GradeVO();
        grade.setSubject("과목");
        grade.setName("park");
        grade.setGrade(95);

        favoriteVO fav = new favoriteVO();
        fav.setColor("blue");
        fav.setAnimal("고양이");
        fav.setNumber(7);

        System.out.println("grade 내용보기 : " + grade);
        System.out.println("favorite 내용보기 : " + fav);
    }

    public static class GradeVO {
        private String subject;
        private String name;
        private int grade;

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getGrade() {
            return grade;
        }

        public void setGrade(int grade) {
            this.grade = grade;
        }

        @Override
        public String toString() {
            return "Grade{" +
                    "subject='" + subject + '\'' +
                    ", name='" + name + '\'' +
                    ", grade=" + grade +
                    '}';
        }
    }

    public static class favoriteVO {
        private String color;
        private String animal;
        private int number;

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getAnimal() {
            return animal;
        }

        public void setAnimal(String animal) {
            this.animal = animal;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        @Override
        public String toString() {
            return "favorite{" +
                    "color='" + color + '\'' +
                    ", animal='" + animal + '\'' +
                    ", number=" + number +
                    '}';
        }
    }
}
