package com.myaws.myapp.service;

import java.util.ArrayList;

import com.myaws.myapp.domain.BoardVo;
import com.myaws.myapp.domain.SearchCriteria;

public interface BoardService {
	public ArrayList<BoardVo> boardSelectAll(SearchCriteria scri);
	
	public int boardTotalCount(SearchCriteria scri) ;
}
