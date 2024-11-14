package com.myaws.myapp.persistance;

import java.util.ArrayList;
import java.util.HashMap;

import com.myaws.myapp.domain.BoardVo;
import com.myaws.myapp.domain.SearchCriteria;



public interface BoardMapper {
	public ArrayList<BoardVo> boardSelectAll(HashMap<String,Object> hm);// �ؽ� ���� �ѱ�� ���̹�Ƽ�� 
	
	public int boardTotalCount(SearchCriteria scri) ; 
	
	public int boardInsert(BoardVo bv) ; // Mybatis �� �޼��� �߰�
	
	public int boardOriginbidxUpdate(int bidx);
	
	public BoardVo boardSelectOne(int bidx) ;
	
	public int  boardViewCntUpdate(int bidx) ;
	
	public int  boardRecomUpdate(BoardVo bv) ;
	
	public int boardDelete(HashMap hm);
	
	public int boardUpdate(BoardVo bv);
	
	public int boardReplyUpdate(BoardVo bv);
	
	public int boardReplyInsert(BoardVo bv);
}
