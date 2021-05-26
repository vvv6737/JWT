package com.example.survey.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.example.survey.admin.model.AdminVO;
import com.example.survey.admin.model.EventVO;
import com.example.survey.admin.service.EventService;
import com.example.survey.model.Pagination;
import com.example.survey.model.ProductVO;
import com.example.survey.service.ProductService;
import com.example.survey.utils.SessionUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;
    @Autowired
    EventService eventService;

    // 웹 브라우저에서 http://localhost:8888/Product/Productinsert 로 호출한다.
    @RequestMapping("/productinsert")
    private String boardInsertForm(Model model, HttpServletRequest request) {

        EventVO eventVO1 = new EventVO();
        List<EventVO> eventlist = eventService.eventList(eventVO1);
        model.addAttribute("eventlist", eventlist);

        AdminVO admin = SessionUtils.getAdmin(request);
        model.addAttribute("admin", admin);

        System.out.println("Controller insert......");
        return "pages/product/productinsert";
    }//end - private String boardInsertForm()

    // Controller 에서 Multipart 를 @RequestParet 어노테이션을 통해 별도의 설정없이 사용할 수 있다.
    @RequestMapping("/insertProc")
    private String boardInsertProc(HttpServletRequest request, @RequestPart MultipartFile productimagefile) throws Exception {

        // 게시글 등록 화면에서 입력한 값들을 실어나르기 위해 ProductDTO를 생성한다.
        ProductVO product = new ProductVO();

        product.setProductname(request.getParameter("productname"));
        product.setProductprice(Integer.parseInt(request.getParameter("productprice")));
        product.setProductsalescnt(Integer.parseInt(request.getParameter("productsalescnt")));
        product.setProductid(Integer.parseInt(request.getParameter("productid")));

        if (productimagefile.isEmpty()) { // 업로드할 파일이 없는 경우
            productService.productInsertService(product); // 게시글만 올린다.
        } else { // 업로드할 파일이 있는 경우

            // FilenameUtils : commons-io defendency를 사용.
            String Productimagefile = productimagefile.getOriginalFilename();
            String ProductimageOriName = productimagefile.getOriginalFilename();
            String fileNameExtension = FilenameUtils.getExtension(ProductimageOriName).toLowerCase();
            File destinationFile;
            String destinationFileName;

            // fileUrl = "uploadFiles 폴더의 위치";
            String productimageUrl = "/Users/mac/Desktop/survey/src/main/resources/static/upload/";

            do {
                destinationFileName = RandomStringUtils.randomAlphanumeric(32) + "." + fileNameExtension;
                destinationFile = new File(productimageUrl + destinationFileName);
            } while (destinationFile.exists());

            // MultipartFile.transferTo() : 요청 시점의 임시 파일을 로컬 파일 시스템에 영구적으로 복사해준다.
            destinationFile.getParentFile().mkdirs();
            productimagefile.transferTo(destinationFile);

            //productService.productInsertService(product); // 게시글 올리기

            // 파일관련 자료를 product테이블에 등록한다.
            product.setProductno(product.getProductno());
            product.setProductimagefile(Productimagefile);
            product.setProductimageName(destinationFileName);
            product.setProductimageOriName(ProductimageOriName);
            product.setProductimageUrl(productimageUrl);

            System.out.println("Product Controller product : " + product);
            productService.productInsertService(product);
        }

        return "redirect:/product/productlist";
    }//end -private String boardInsertProc (HttpServletRequest request,@RequestPart MultipartFile productimagefile) throws Exception

    // 목록 보여주기
    @RequestMapping(value = "/productlist", method = RequestMethod.GET)
    private String ProductList(Model model, @RequestParam(required = false, defaultValue = "1") int page,
                               @RequestParam(required = false, defaultValue = "1") int range) throws Exception {

        // 전체 게시글 개수
        int listCnt = productService.getProductListCnt();;

        // Pagination 객체생성
        Pagination pagination = new Pagination();

        pagination.pageInfo(page, range, listCnt);

        model.addAttribute("pagination", pagination);

        model.addAttribute("list", productService.productListService(pagination));

        return "pages/product/productlist";
    }//end - private String ProductList( Model model, @RequestParam(required = false, defaultValue = "1") int page,
    //@RequestParam(required = false, defaultValue = "1") int range) throws Exception

    // 게시글 카테고리 목록 보여주기
    @RequestMapping(value = "/productlist/{productid}", method = RequestMethod.GET)
    private String ProductcateList(@PathVariable int productid, Model model) throws Exception {

        // 전체 게시글 개수
        int listCnt = productService.getProductListCnt();

        // Pagination 객체생성
        Pagination pagination = new Pagination();

        int page = 1;
        int range = 1;
        pagination.pageInfo(page, range, listCnt);
        pagination.setProductid(productid);
        System.out.println("*****productid : " + pagination.getProductid());

        model.addAttribute("pagination", pagination);

        model.addAttribute("list", productService.productcateListService(pagination));

        return "pages/product/productlist";
    }//end - private String ProductcateList(@PathVariable int productid, Model model) throws Exception

    // 게시글 번호에 해당하는 상세정보화면
    @RequestMapping("/productdetail/{productno}")
    private String boardDetail(@PathVariable int productno, HttpServletRequest request, Model model) throws Exception {

        // bno에 해당하는 자료를 찾아와서 model에 담는다.
        model.addAttribute("productdetail", productService.productDetailService(productno)); // 게시글의 정보를 가져와서 저장한다.
        //model.addAttribute("files", productService.fileDetailService(bno)); // 파일의 정보를 가져와서 저장한다.


        return "pages/product/productdetail";
    }

    //메인 검색 기능
    @RequestMapping("/searchList")
    private String searchList(Model model, HttpServletRequest request) throws Exception {

        model.addAttribute("search", productService.search(request.getParameter("searchName")));

        return "pages/product/searchList";
    }//end - private String searchList( Model model, HttpServletRequest request) throws Exception

    // 게시글 수정 화면
    @RequestMapping(value = "/Update/{productno}", method = RequestMethod.GET)
    private String getUpdate(@PathVariable int productno, Model model) throws Exception {
        model.addAttribute("detail", productService.productDetailService(productno));
        return "pages/product/Update";
    }// end - public String getUpdate(@PathVariable int bno,Model model) throws
    // Exception

    // 게시글 수정 화면에서 수정할 자료를 업데이트한다.
    @RequestMapping("/updateProc")
    private String productUpdateProc(HttpServletRequest request, @RequestParam int productno) throws Exception {

        ProductVO productDTO = new ProductVO();

        //업데이트 할 정보를 요청한다.
        productDTO.setProductimagefile(request.getParameter("productimagefile"));
        productDTO.setProductimageName(request.getParameter("productimageName"));
        productDTO.setProductimageOriName(request.getParameter("productimageOriName"));
        productDTO.setProductimageUrl(request.getParameter("productimageUrl"));

        productDTO.setProductname(request.getParameter("productname"));
        productDTO.setProductprice(Integer.parseInt(request.getParameter("productprice")));
        productDTO.setProductsalescnt(Integer.parseInt(request.getParameter("productsalescnt")));
        productDTO.setProductno(Integer.parseInt(request.getParameter("productno")));

        productService.update(productDTO);
        return "redirect:/product/productdetail/" + request.getParameter("productno");

    }//end - private String productUpdateProc(HttpServletRequest request,@RequestParam int productno) throws Exception

    // 글 번호에 해당하는 자료를 삭제한다.
    @RequestMapping("/delete/{productno}")
    private String productDelete(@PathVariable int productno) throws Exception {

        productService.productDeleteService(productno);
        return "redirect:/product/productlist";
    }//end - private String productDelete(@PathVariable int productno) throws Exception


}// end - public class ProductController 