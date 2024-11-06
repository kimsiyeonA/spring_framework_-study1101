package  com.myaws.myapp.domain;

import org.springframework.stereotype.Component;

@Component // 서비스 Component, 컨트롤러 Component 조상격
public class SearchCriteria extends Criteria {
		
		private String searchType ; // 검색선택
		private String keyword; // 검색어
		
		
		public String getSearchType() {
			return searchType;
		}
		public void setSearchType(String searchType) {
			this.searchType = searchType;
		}
		public String getKeyword() {
			return keyword;
		}
		public void setKeyword(String keyword) {
			this.keyword = keyword;
		}
		

}
