package com.example.survey.service;

import com.example.survey.mapper.BoardMapper;
import com.example.survey.model.BoardVO;
import com.example.survey.model.PagingVO;
import com.example.survey.model.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    @Autowired
    BoardMapper boardMapper;

    //리스트 조회
    public List<BoardVO> listBoard(PagingVO pagingVO) {
        return boardMapper.listBoard(pagingVO);
    }

    //전체 페이지 조회
    public int pageList (PagingVO pagingVO) {
        return boardMapper.pageList(pagingVO);
    }

    // (검색어가 포함된) 전체 목록 갯수
    public int totalListCount (PagingVO pagingVO) {
        return boardMapper.totalListCount(pagingVO);
    }

    //검색
    public String Search (PagingVO pagingVO) {
        boardMapper.Search(pagingVO);
        System.out.println("searchText : " + pagingVO.getSearchText());
        return boardMapper.Search(pagingVO);
    }

    //글쓰기
    public int boardInsert(BoardVO boardVO) {
        return boardMapper.boardInsert(boardVO);
    }

    //게시글 상세보기
    public BoardVO boardDetail(int seq) {
        return boardMapper.boardDetail(seq);
    }

    //게시글 수정
    public BoardVO boardUpdate(BoardVO boardVO){
        boardMapper.boardUpdate(boardVO);
        System.out.println("title : " + boardVO.getTitle());
        System.out.println("content : " + boardVO.getContent());
        return boardVO;
    }

    //개시글 삭제
    public BoardVO boardDelete(BoardVO boardVO) {
        boardMapper.boardDelete(boardVO);
        return boardVO;
    }

    //조회수
    public int boardHit(int seq) {
        return boardMapper.boardHit(seq);
    }

    //설문조사 입력
    public int testInsert(BoardVO boardVO) {
        return boardMapper.testInsert(boardVO);
    }

    /**
     * 설문조사 참여여부 판단
     * @param eventSeq 참여한 이벤트 시퀀스
     * @param userVO
     * @return boolean 참여했다면 true, 아직 미참여인 경우 false
     */
    public BoardVO isFinishSurvey(String id) {
        return boardMapper.isFinishSurvey(id);
    }
}