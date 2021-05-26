package com.example.survey.mapper;

import com.example.survey.model.Pagination;
import com.example.survey.model.ProductVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {
	
	//게시글의 개수
	public int productCount() throws Exception;

	public int getproductListCnt() throws Exception;
	
	//게시글 목록 보기
	public List<ProductVO> productList(Pagination pagination) throws Exception;
	
	//게시글 작성
	public int productInsert(ProductVO product) throws Exception;
	
	//게시글 상제정보
	public ProductVO productDetail(int productno) throws Exception;
	
	//파일올리기
	public int fileInsert(ProductVO product) throws Exception;
		
	//파일 상세 정보
	public ProductVO fileDetail(int Productno) throws Exception;
    
	//게시글 삭제
	public int productDelete(int Productno) throws Exception;

	//게시글 수정
	public int productUpdate(ProductVO product) throws Exception;
	
	//product 카테고리
    public List<ProductVO> ProductcateList(Pagination pagination) throws Exception;
    
    //메인 검색 기능
    public List<ProductVO> search(String searchName) throws Exception;

}
