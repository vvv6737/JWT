package com.example.survey.controller;

import com.example.survey.admin.model.AdminVO;
import com.example.survey.admin.model.EventVO;
import com.example.survey.admin.service.AdminService;
import com.example.survey.admin.service.EventService;
import com.example.survey.model.BoardVO;
import com.example.survey.model.ReplyVO;
import com.example.survey.model.UserVO;
import com.example.survey.service.*;
import com.example.survey.utils.SessionUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.*;

@Controller
public class BoardController {
    private static final Logger log = LoggerFactory.getLogger(BoardController.class);
    final String key = "JWT";

    @Autowired
    BoardService boardService;

    @Autowired
    UserService userService;

    @Autowired
    LoginService loginService;

    @Autowired
    ReplyService replyService;

    @Autowired
    AdminService adminService;

    @Autowired
    LoginService loginService1;

    @Autowired
    EventService eventService;

    @Autowired
    CartService cartService;

    //게시판 리스트
    //post로 검색처리를 했으나 폼 메소드를 버튼으로바꾸어 검색창에 주소가 뜨게한다.
    @RequestMapping(value = "/surveylist/{seq}", method = {RequestMethod.GET, RequestMethod.POST})
    public String list(@PathVariable int seq, Model model, HttpServletRequest request) throws Exception {

        AdminVO admin = SessionUtils.getAdmin(request);
        model.addAttribute("admin", admin);

        //로그인에 따라 글쓰기 혹은 로그인 버튼을 보여주기 위해 데이터를 가져온다
        UserVO user = SessionUtils.getUser(request);
        model.addAttribute("user", user);

        //검색결과 목록
        List<BoardVO> boardlist = eventService.eventBoard(seq);
        model.addAttribute("boardlist", boardlist);

        EventVO eventVO1 = new EventVO();
        List<EventVO> eventlist = eventService.eventList(eventVO1);
        model.addAttribute("eventlist", eventlist);

        EventVO eventVO = eventService.eventSession(seq);
        SessionUtils.setEventList(eventVO, request);
        model.addAttribute("eventSession", eventVO);

        EventVO eventVO2 = SessionUtils.getEventList(request);
        model.addAttribute("events", eventVO2);

        //카트 목록 갯수
        int cartCount = cartService.cartCount();
        model.addAttribute("cartcount", cartCount);

        model.addAttribute("cartList", cartService.cartListService());

        return "pages/survey/list";
    }

    //글쓰기
    @PostMapping(value = "/list/writeBoard")
    public String insertBoard(BoardVO boardVO, HttpServletRequest request) {
        UserVO user = SessionUtils.getUser(request);
        if (user != null) {
            boardVO.setUserSeq(user.getSeq());
            boardService.boardInsert(boardVO);
            return "redirect:/list";
        } else {
            return "redirect:/login";
        }
    }

    // 게시글 상세보기
    @RequestMapping(value = "/surveylist/boardDetail/{seq}", method = RequestMethod.GET)
    private String boardDetail(@PathVariable int seq, Model model, HttpServletRequest request) throws Exception {
        log.info("board Detail get...");
        BoardVO vo = boardService.boardDetail(seq);
        model.addAttribute("boardDetail", vo);

        EventVO eventVO1 = new EventVO();
        List<EventVO> eventlist = eventService.eventList(eventVO1);
        model.addAttribute("eventlist", eventlist);

        UserVO user = SessionUtils.getUser(request);
        model.addAttribute("user", user);

        AdminVO admin = SessionUtils.getAdmin(request);
        model.addAttribute("admin", admin);

        EventVO eventVO2 = SessionUtils.getEventList(request);
        model.addAttribute("events", eventVO2);

        //세션 값을 이용해서 유저를 가져온다
        AdminVO admin1 = SessionUtils.getAdmin(request);
        //SampleVO sampleVO = SessionUtils.getUser(request);

        //유저 네임을 가져오되 이름이 없으면 NO_NAME값으로 넣어서 구현한다.
        model.addAttribute("userName", admin1 == null ? "익명" : admin1.getManagerName());

        //조회수
        model.addAttribute("boardHit", boardService.boardHit(seq));

        //댓글목록
        List<ReplyVO> replyList = replyService.replyList(seq);
        model.addAttribute("replyList", replyList);

        //카트 목록 갯수
        int cartCount = cartService.cartCount();
        model.addAttribute("cartcount", cartCount);

        model.addAttribute("cartList", cartService.cartListService());

        return "pages/board/boardDetail";
    }

    //토큰 발급
    @GetMapping("/token/{id}")
    public String TokenIssuance(@PathVariable String id, Model model, HttpServletRequest request, RedirectAttributes attributes, HttpServletResponse response) {
        try {
            UserVO userVO = userService.urlTest(id);

            String jwt = createToken(userVO);
            System.out.println(jwt);

            model.addAttribute("jwt", jwt);

            UserVO userVO1 = SessionUtils.getUser(request);
            model.addAttribute("name", userVO1 == null ? "NO NAME" : userVO1.getName());

            Map<String, Object> claimMap = verifyJWT(jwt);
            System.out.println(claimMap); // 토큰이 만료되었거나 문제가있으면 null

            model.addAttribute("claimMap", claimMap);

            if (SessionUtils.setUser(userVO, request)) {
                model.addAttribute("test", userVO);
                attributes.addFlashAttribute("msg", true);
                attributes.addFlashAttribute("jwt", jwt);
                attributes.addFlashAttribute("copybtn", true);
                return "redirect:/userlist";
            } else {
                return "pages/token/NotInfo";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "pages/token/NotInfo";
        }
    }

    //토큰 생성
    public String createToken(UserVO userVO) {

        //Header 부분 설정
        Map<String, Object> headers = new HashMap<>();
        headers.put("typ", "JWT");
        headers.put("alg", "HS256");

        //payload 부분 설정
        Map<String, Object> payloads = new HashMap<>();
        //payloads.put("data", "My JWT");
        payloads.put("seq", userVO.getSeq());
        payloads.put("id", userVO.getId());
        payloads.put("name", userVO.getName());
        payloads.put("email", userVO.getEmail());
        payloads.put("tel", userVO.getTel());
        payloads.put("eventSeq", userVO.getEventSeq());

        //Long expiredTime = 1000 * 60L * 60L * 2L; // 토큰 유효 시간 (2시간)
        Long expiredTime = 30 * 60 * 1000L; // 토큰 유효 시간 (30분)


        Date ext = new Date(); // 토큰 만료 시간
        ext.setTime(ext.getTime() + expiredTime);

        // 토큰 Builder
        String jwt = Jwts.builder()
                .setHeader(headers) // Headers 설정
                .setClaims(payloads) // Claims 설정
                //.setSubject(sampleVO.getEmail()) // 토큰 용도(제목)
                .setExpiration(ext) // 토큰 만료 시간 설정
                .signWith(SignatureAlgorithm.HS256, key.getBytes()) // HS256과 Key로 Sign
                .compact(); // 토큰 생성

        return jwt;
    }

    //토큰 검증
    public Map<String, Object> verifyJWT(String jwt) throws UnsupportedEncodingException {
        Map<String, Object> claimMap = null;
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(key.getBytes("UTF-8")) // Set Key
                    .parseClaimsJws(jwt) // 파싱 및 검증, 실패 시 에러
                    .getBody();
            claimMap = claims;

        } catch (ExpiredJwtException e) { // 토큰이 만료되었을 경우
            System.out.println(e);
        } catch (Exception e) { // 그외 에러났을 경우
            System.out.println(e);
        }
        return claimMap;
    }

    // 게시글 수정 화면
    @GetMapping("/list/boardUpdate/{seq}")
    private String boardUpdate(@PathVariable int seq, Model model, HttpServletRequest request, HttpServletResponse response) {
        System.out.println("seq : " + seq);
        BoardVO boardVO = boardService.boardDetail(seq);
        model.addAttribute("board", boardVO);
        return "pages/board/boardUpdate";
    }

    // 게시글 수정 API
    @PostMapping("/list/boardUpdateProc")
    private String boardUpdateProc(HttpServletRequest request, HttpServletResponse response) {
        int seq = Integer.parseInt(request.getParameter("seq"));
        String title = request.getParameter("title");
        String content = request.getParameter("content");

        BoardVO boardVO = new BoardVO(seq, title, content);
        boardService.boardUpdate(boardVO);
        return "redirect:/list/boardDetail/" + seq;
    }

    //게시글 삭제
    @GetMapping("/list/boardDelete/{seq}")
    private String boardDelete(@PathVariable int seq, BoardVO boardVO) {
        boardService.boardDelete(boardVO);
        return "redirect:/surveylist/1";
    }
}