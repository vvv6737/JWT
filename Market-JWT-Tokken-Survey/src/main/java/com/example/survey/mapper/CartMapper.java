package com.example.survey.mapper;

import java.util.List;

import com.example.survey.model.CartVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CartMapper {

	//장바구니 목록 갯수
	int cartCount() throws Exception;

	//장바구니 목록 보기
	List<CartVO> cartList() throws Exception;
	
	//장바구니 등록
	int insertCart(CartVO cartDetailDTO) throws Exception;
	
	//삭제
	int cartDelete(int cartno) throws Exception;

}
