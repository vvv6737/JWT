package com.example.survey.mapper;

import com.example.survey.model.BoardVO;
import com.example.survey.model.PagingVO;
import com.example.survey.model.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BoardMapper {
    //리스트 불러오기
    List<BoardVO> listBoard(PagingVO pagingVO);

    //전체 페이지 조회
    int pageList(PagingVO pagingVO);

    // (검색어가 포함된) 전체 목록 갯수
    int totalListCount(PagingVO pagingVO);

    //검색
    String Search(PagingVO pagingVO);

    //입력
    int boardInsert(BoardVO boardVO);

    //게시글 상세보기
    BoardVO boardDetail(int model);

    //게시글 수정
    int boardUpdate(BoardVO boardVO);

    //게시글 삭제
    int boardDelete(BoardVO boardVO);

    //조회
    int boardHit(int seq);

    //설문조사 입력
    int testInsert(BoardVO boardVO);

    BoardVO isFinishSurvey (String id);
}
