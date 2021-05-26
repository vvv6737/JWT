package com.example.demo;

import java.util.Scanner;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DescTest {
    public static void main(String args[]) {
        int sixsu[] = new int[6];  // int형 배열 선언
        Scanner sc = new Scanner(System.in);

        // 6개의 숫자 입력
        System.out.println("6개의 숫자를 입력하세요");
        for (int i = 0; i < sixsu.length; i++) {
            sixsu[i] = sc.nextInt();
        }

        // 입력한 값 출력
        System.out.print("입력된 값은 : ");
        for (int j = 0; j < 6; j++) {
            System.out.print(sixsu[j] + " ");
        }
        System.out.println();

        // 입력받은 수를 작은 순서대로 넣기
        for (int i = 0; i < sixsu.length; i++) {
            for (int j = i + 1; j < sixsu.length; j++) {
                if (sixsu[i] > sixsu[j]) {
                    int tmep = sixsu[i];
                    sixsu[i] = sixsu[j];
                    sixsu[j] = tmep;
                }
            }
        }

        // 결과값 출력
        System.out.print("작은 수부터 출력 : ");
        for (int i = 0; i < 6; i++) {
            System.out.print(sixsu[i] + " ");
        }
        System.out.println();

        // 입력받은 수를 큰 순서대로 넣기
        for (int i = 0; i < sixsu.length; i++) {
            for (int j = i + 1; j < sixsu.length; j++) {
                if (sixsu[i] < sixsu[j]) {
                    int tmep = sixsu[i];
                    sixsu[i] = sixsu[j];
                    sixsu[j] = tmep;
                }
            }
        }

        // 결과값 출력
        System.out.print("큰 수부터 출력 : ");
        for (int i = 0; i < 6; i++) {
            System.out.print(sixsu[i] + " ");
        }
        System.out.println();
    }
}