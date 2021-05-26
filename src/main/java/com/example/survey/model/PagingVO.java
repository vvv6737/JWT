package com.example.survey.model;

public class PagingVO {

    // 총 보여줄 리스트의 갯수
    private final int DEFAULT_ROWS = 3000;
    private final int DEFAULT_PAGE_NUM = 1;

    /**
     * 현재 페이지 번호
     */
    private int pageNum = DEFAULT_PAGE_NUM;
    /**
     * 한페이지에 노출 갯수
     */
    private int rows = DEFAULT_ROWS;

    /**
     * DB 검색결과값의 시작 인덱스 번호
     */
    private int offset;

    /**
     * 전체 페이지 갯수
     */
    private int totalPages;

    /**
     * 검색어
     */
    private String searchText;

    public String getStartPage() {
        return startPage;
    }

    public void setStartPage(String startPage) {
        this.startPage = startPage;
    }

    public String getEndPage() {
        return endPage;
    }

    public void setEndPage(String endPage) {
        this.endPage = endPage;
    }

    private String startPage;

    private String endPage;

    public int getPageNum() {
        try {
            return pageNum;
        } catch (Exception ex) {
            ex.printStackTrace();
            return DEFAULT_PAGE_NUM;
        }

    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getRows() {
        try {
            return rows;
        } catch (Exception ex) {
            return DEFAULT_ROWS;
        }
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }
}