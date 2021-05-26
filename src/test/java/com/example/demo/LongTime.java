package com.example.demo;

import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
public class LongTime {
    public static void main(String[] args) throws ParseException {
        long lData = 1620344741, lData2;
        String dateFormatStringTime;
        System.out.println("lData:" + lData); // long으로 바꿈

        lData = lData * 1000; // 바로 1000을 곱한 수를 넣으면 범위 초과 에러가 나기 때문에
        Date date1 = new Date(lData);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormatStringTime = dateFormat.format(date1);
        System.out.print("long->date->String 으로 변환된 날짜 : ");
        System.out.println(dateFormatStringTime);

        // ↑ long->date, ↓ date -> long

        Date date2 = dateFormat.parse(dateFormatStringTime);
        System.out.println("[String->date]" + date2);
        lData2 = date2.getTime(); // date타입을 long으로 변환 getTime
        System.out.println("lData2 : " + lData2);
        lData2 = lData2 / 1000;  // 다시 원래의 값으로 돌아옴
        System.out.println("lData2 : " + lData2);
    }
}
