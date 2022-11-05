package com.itwillbs.mvc_board.vo;

// 페이징 처리를 위한 정보를 저장할 PageInfo 클래스 정의
public class PageInfo {
	private int pageNum;
	private int listLimit;
	private int listCount;
	private int pageListLimit;
	private int maxPage;
	private int startPage;
	private int endPage;
	
	// 기본 생성자
	public PageInfo() {}

	// 파라미터 생성자
	public PageInfo(int pageNum, int listLimit, int listCount, int pageListLimit, int maxPage, int startPage, int endPage) {
		this.pageNum = pageNum;
		this.listLimit = listLimit;
		this.listCount = listCount;
		this.pageListLimit = pageListLimit;
		this.maxPage = maxPage;
		this.startPage = startPage;
		this.endPage = endPage;
	}

	// Getter & Setter
	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getListLimit() {
		return listLimit;
	}

	public void setListLimit(int listLimit) {
		this.listLimit = listLimit;
	}

	public int getListCount() {
		return listCount;
	}

	public void setListCount(int listCount) {
		this.listCount = listCount;
	}

	public int getPageListLimit() {
		return pageListLimit;
	}

	public void setPageListLimit(int pageListLimit) {
		this.pageListLimit = pageListLimit;
	}

	public int getMaxPage() {
		return maxPage;
	}

	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	@Override
	public String toString() {
		return "PageInfo [pageNum=" + pageNum + ", listLimit=" + listLimit + ", listCount=" + listCount
				+ ", pageListLimit=" + pageListLimit + ", maxPage=" + maxPage + ", startPage=" + startPage
				+ ", endPage=" + endPage + "]";
	};
}
