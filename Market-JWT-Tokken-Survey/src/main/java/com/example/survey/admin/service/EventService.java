package com.example.survey.admin.service;

import com.example.survey.admin.mapper.EventMapper;
import com.example.survey.admin.model.EventVO;
import com.example.survey.model.BoardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    @Autowired
    EventMapper eventMapper;

    public List<EventVO> eventList(EventVO eventVO) {
        return eventMapper.eventList(eventVO);
    }

    public int eventInsert(EventVO eventVO) {
        return eventMapper.eventInsert(eventVO);
    }

    public EventVO eventDetail(int seq) {
        return eventMapper.eventDetail(seq);
    }

    public EventVO eventDelete(EventVO eventVO) {
        eventMapper.eventDelete(eventVO);
        return eventVO;
    }

    //이벤트 세션
    public EventVO eventSession(int seq) {
        return eventMapper.eventSession(seq);
    }

    //이벤트 안의 리스트
    public List<BoardVO> eventBoard(int seq) {
        return eventMapper.eventBoard(seq);
    }

    public int eventBoardInsert(BoardVO boardVO) {
        return eventMapper.eventBoardInsert(boardVO);
    }

}
