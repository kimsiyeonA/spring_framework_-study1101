package com.myaws.myapp.persistance;

import java.util.ArrayList;
import java.util.HashMap;

import com.myaws.myapp.domain.BoardVo;
import com.myaws.myapp.domain.SearchCriteria;



public interface BoardMapper {
	public ArrayList<BoardVo> boardSelectAll(HashMap<String,Object> hm);// 해시 맵을 넘기는 마이바티스 
	public int boardTotalCount(SearchCriteria scri) ; 
	public int boardInsert(BoardVo bv) ; // Mybatis 용 메서드 추가
	public int boardOriginbidxUpdate(int bidx);
	public BoardVo boardSelectOne(int bidx) ;
	public int  boardViewCntUpdate(int bidx) ;
	
	public int  boardRecomUpdate(BoardVo bv) ;
	public int boardDelete(HashMap hm);
}
