package com.example.survey.controller;

import com.example.survey.admin.model.AdminVO;
import com.example.survey.service.BoardService;
import com.example.survey.service.ReplyService;
import com.example.survey.utils.SessionUtils;
import com.example.survey.model.BoardVO;
import com.example.survey.model.ReplyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@ResponseBody
public class ReplyController {

    @Autowired
    ReplyService replyService;

    @Autowired
    BoardService boardService;

    //댓글 작성
    @RequestMapping(value = "/list/replyInsert", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    private int replyInsert(@RequestParam int seq, @RequestParam String name, @RequestParam String recontent, Model model, HttpServletRequest request) {

        BoardVO boardVO = new BoardVO(seq);
        boardService.boardDetail(boardVO.getSeq());

        ReplyVO replyVO = new ReplyVO();
        replyVO.setSeq(seq);
        replyVO.setName(name);
        replyVO.setRecontent(recontent);

        AdminVO admin1 = SessionUtils.getAdmin(request);
        model.addAttribute("admin", admin1);

        return replyService.replyInsert(replyVO);
    }

    //댓글 수정
    @PostMapping("/list/replyUpdate")
    private int replyUpdate(@RequestParam int reseq, @RequestParam String recontent) {

        ReplyVO replyVO = new ReplyVO();
        replyVO.setReseq(reseq);
        replyVO.setRecontent(recontent);

        return replyService.replyUpdate(replyVO);
    }

    //댓글 삭제
    @RequestMapping(value = "/list/replyDelete/{reseq}", method = {RequestMethod.GET, RequestMethod.POST})
    private int replyDelete(@PathVariable int reseq) {
        return replyService.replyDelete(reseq);
    }
}