package com.example.survey.service;

import com.example.survey.mapper.CartMapper;
import com.example.survey.model.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    @Autowired
    CartMapper cartMapper;

    public int cartCount() throws Exception{
        return cartMapper.cartCount();
    }

    //게시글 목록 보기
    public List<CartVO> cartListService() throws Exception {
        return cartMapper.cartList();
    }

    //장바구니 등록
    public int insertCart(CartVO cartVO) throws Exception {
        return cartMapper.insertCart(cartVO);
    }

    //삭제
    public int cartDeleteService(int cartno) throws Exception {
        return cartMapper.cartDelete(cartno);
    }

}
