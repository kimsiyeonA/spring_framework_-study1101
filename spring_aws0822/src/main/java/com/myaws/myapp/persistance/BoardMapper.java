package com.myaws.myapp.persistance;

import java.util.ArrayList;
import java.util.HashMap;

import com.myaws.myapp.domain.BoardVo;
import com.myaws.myapp.domain.SearchCriteria;



public interface BoardMapper {
	public ArrayList<BoardVo> boardSelectAll(HashMap<String,Object> hm);// 해시 맵을 넘기는 마이바티스 
	public int boardTotalCount(SearchCriteria scri) ; 
}
