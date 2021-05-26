package com.example.survey.mapper;

import com.example.survey.model.ReplyVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface ReplyMapper {

    //댓글 목록
    public List<ReplyVO> replyList(int seq) throws Exception;

    //댓글 입력
    int replyInsert(ReplyVO replyVO);

    //댓글 수정
    int replyUpdate(ReplyVO replyVO);

    //댓글 삭제
    int replyDelete(int reseq);
}
