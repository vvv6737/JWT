package com.example.survey.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.example.survey.admin.model.AdminVO;
import com.example.survey.admin.model.EventVO;
import com.example.survey.admin.service.EventService;
import com.example.survey.model.CartVO;
import com.example.survey.model.UserVO;
import com.example.survey.service.CartService;
import com.example.survey.service.UserService;
import com.example.survey.utils.SessionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {
	
	private static final Logger logger = LoggerFactory.getLogger(CartController.class);

	@Autowired
	CartService cartService;

	@Autowired
	UserService userService;

	@Autowired
	EventService eventService;

	//카트 목록 보여주기
	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	private String cartList(CartVO cartDetailDTO, EventVO eventVO, Model model, HttpServletRequest request) throws Exception {
		logger.info("cartlist : " + cartDetailDTO);

		AdminVO admin = SessionUtils.getAdmin(request);
		model.addAttribute("admin", admin);

		UserVO userVO = SessionUtils.getUser(request);
		model.addAttribute("user", userVO);

		model.addAttribute("list", cartService.cartListService());

		//카트 목록 갯수
		int cartCount = cartService.cartCount();
		model.addAttribute("cartcount", cartCount);

		List<EventVO> eventlist = eventService.eventList(eventVO);
		model.addAttribute("eventlist", eventlist);

		if (userVO != null) {
			return "pages/cart/list";
		} else {
			return "redirect:/login";
		}
	}
	
	//카트에 담기
	@RequestMapping(value = "/insertCart", method = { RequestMethod.GET, RequestMethod.POST})
	//@RequestMapping(value = "/insertCart", method = RequestMethod.POST)
	private String insertCart(CartVO cartVO, Model model, HttpServletRequest request, HttpSession session) throws Exception {
		logger.info("insertCart()....");
		
		//CartDTO cartDTO = new CartDTO();
		
		//cartDTO.setCartuserid(cartuserid);
		logger.info("cartDTO : " + cartVO);
		String cartuserid = request.getParameter("cartuserid");
		logger.info("cartuserid : " + cartuserid);

		UserVO userVO = SessionUtils.getUser(request);
		model.addAttribute("user", userVO);

		model.addAttribute("inserCart", cartService.insertCart(cartVO));

		if (userVO == null) {
			return "redirect:/login";
		} else {
			return "redirect:/cart/list";
		}
	}//end - private String insertCart(CartDTO cartDTO, Model model, HttpServletRequest request) throws Exception
	
	//삭제
	@RequestMapping("/cartDelete/{cartno}")
	private String cartDelete(@PathVariable int cartno, Model model) throws Exception {
		
		logger.info("delete" + cartno);
		cartService.cartDeleteService(cartno);
		return "redirect:/cart/list";
	}//end - private String cartDelete(@PathVariable int cartno, Model model) throws Exception
	
	
	
}



