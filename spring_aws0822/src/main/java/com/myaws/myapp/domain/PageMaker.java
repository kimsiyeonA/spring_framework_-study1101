
package  com.myaws.myapp.domain;

import org.springframework.stereotype.Component;

@Component
public class PageMaker {
	
	private int diplayPageNum = 10; 
	private int startPage; 
	private int endPage; 
	private int totalCount; 
	
 	private boolean prev; 
 	private boolean next; 
 	
 	private SearchCriteria scri;

	public int getDiplayPageNum() {
		return diplayPageNum;
	}

	public void setDiplayPageNum(int diplayPageNum) {
		this.diplayPageNum = diplayPageNum;
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

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) { 
		this.totalCount = totalCount;
		calcData(); 
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public SearchCriteria getScri() {
		return scri;
	}

	public void setScri(SearchCriteria scri) {
		this.scri = scri;
	}
	
	private void calcData() { 
		
		
		endPage = (int)(Math.ceil(scri.getPage()/(double)diplayPageNum)*diplayPageNum); 
		
		startPage = (endPage-diplayPageNum)+1;
		
		int tempEndPage = (int)(Math.ceil(totalCount/(double)scri.getPerPageNum()));
		
		if(endPage > tempEndPage) {
			endPage = tempEndPage; 
		}
		
		prev=(startPage == 1? false : true); 
		next=(endPage*scri.getPerPageNum() >= totalCount? false : true);
	}
}