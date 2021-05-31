package com.example.survey.admin.mapper;

import com.example.survey.admin.model.EventVO;
import com.example.survey.model.BoardVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EventMapper {

    //이벤트 게시물
    List<EventVO> eventList(EventVO eventVO);

    //이벤트 게시물 입력
    int eventInsert(EventVO eventVO);

    //이벤트 상세보기
    EventVO eventDetail(int seq);

    //이벤트 삭제
    int eventDelete(EventVO eventVO);

    //이벤트 세션
    EventVO eventSession(int seq);

    //이벤트 세션 리스트
    List<BoardVO> eventBoard(int seq);

    int eventBoardInsert (BoardVO boardVO);


}
