package com.example.survey.service;

import com.example.survey.mapper.ProductMapper;
import com.example.survey.model.Pagination;
import com.example.survey.model.ProductVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
	
	//로깅을 위한 변수
	private static final Logger logger = LoggerFactory.getLogger(ProductService.class);
	
	@Autowired
	ProductMapper productMapper;
	
	public int getProductListCnt() throws Exception{
		return productMapper.getproductListCnt();
	}

	//게시글 목록 보기
	public List<ProductVO> productListService(Pagination pagination) throws Exception {
		System.out.println("productListService : " + pagination);
		return productMapper.productList(pagination);
	}
	//게시글 등록
	public int productInsertService(ProductVO product) throws Exception {
		return productMapper.productInsert(product);
	}
	//파일 올리기
	public int fileInsertService(ProductVO product) throws Exception {
		return productMapper.fileInsert(product);
	}
	
	public ProductVO fileDetailService(int productno) throws Exception {
		return productMapper.fileDetail(productno);
	}
	//게시글 ProductVO
	public ProductVO productDetailService(int productno) throws Exception {
		return productMapper.productDetail(productno);
	}
	//게시글 삭제		
	public int productDeleteService(int productno, String productimageName) throws Exception {
		return productMapper.productDelete(productno, productimageName);
	}
	//게시글 수정
	public int update(ProductVO productDTO) throws Exception{
		logger.info("service update..... " + productDTO);
		System.out.println("Productname : " + productDTO.getProductname());
		System.out.println("Productprice : " + productDTO.getProductprice());
		System.out.println("Productsalescnt : " + productDTO.getProductsalescnt());
		return productMapper.productUpdate(productDTO );
	}
	//게시글 카테고리목록 보기
	public List<ProductVO> productcateListService(Pagination pagination) throws Exception {
		System.out.println("productListService : " + pagination);
		return productMapper.ProductcateList(pagination);
	}
//	// 서비스는 매퍼를 호출한다.
//	@Resource(name = "com.edu.Product.mapper.ProductMapper")
//	ProductMapper ProductMapper;

	//메인 검색 기능
	public List<ProductVO> search(String searchName) throws Exception{
		logger.info("service search...." + searchName);
		
		return productMapper.search(searchName);
	}
}
