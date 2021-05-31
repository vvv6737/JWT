package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
class SurveyApplicationTests {

    @Test
    void contextLoads() {
        // curPage = 전체 페이지 수
        int curPage = 0;
        int totalPages = 0;
        int rows = 10;
        
        //게시물 갯수
        int DB_ROWS = 55;
        
        //10페이지가 지나서 5페이지가 남았는데 다음페이지에 나머지를 보일것인지에 대한 계산
        int tmp = DB_ROWS / rows;
        //나머지가 1보다 클경우 다음 페이지로 보내도록 하는 계산
        int tmpRemain = DB_ROWS % rows > 0 ? 1 : 0;

        totalPages = tmp + tmpRemain;

        System.out.println("#### TOTAL PAGE: " + totalPages);

        ////////////////////////////////////////////////////////
        
        //선택 페이지수
        curPage = 5;
        
        //보여줄 목록
        int pagingSize = 3;

        ArrayList<Integer> resultList = calcPagingList(curPage, totalPages, pagingSize);
        //전체 페이지
        System.out.println("#### CURRENT PAGE: " + curPage);
        //보여줄 페이지 수
        System.out.println("#### resultList: " + resultList.toString());
    }

    public ArrayList<Integer> calcPagingList(int currentPageNum, int totalCount, int pagingSize) {

        ArrayList<Integer> pageArr = new ArrayList<>();
        if (currentPageNum + (pagingSize - 1) <= totalCount) {
            // 정상처리되어 목록을 추가한다.
            for (int idx = 0; idx < pagingSize; idx++) {
                pageArr.add(currentPageNum + idx);
            }
        } else {
            // 예외처리 목록을 만들어서 추가한다.
            int tmpPagingSize = 0;
            int loopCount = 0;
            while (loopCount < pagingSize) {
                pageArr.add(totalCount - tmpPagingSize );
                tmpPagingSize += 1;
                loopCount += 1;
            }
        }
        return pageArr;
    }

    ////////////////
}

