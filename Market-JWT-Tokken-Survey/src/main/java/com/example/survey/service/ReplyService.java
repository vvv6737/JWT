package com.example.survey.service;

import com.example.survey.mapper.ReplyMapper;
import com.example.survey.model.ReplyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReplyService {

    @Autowired
    ReplyMapper replyMapper;

    //댓글 목록
    public List<ReplyVO> replyList(int seq) throws Exception {
        return replyMapper.replyList(seq);
    }

    //댓글 입력
    public int replyInsert(ReplyVO replyVO) {
        return replyMapper.replyInsert(replyVO);
    }

    //댓글 수정
    public int replyUpdate(ReplyVO replyVO) {
        return replyMapper.replyUpdate(replyVO);
    }

    //댓글 삭제
    public int replyDelete(int reseq) {
        return replyMapper.replyDelete(reseq);
    }
}