package com.myaws.myapp.service;

import java.util.ArrayList;

import com.myaws.myapp.domain.BoardVo;
import com.myaws.myapp.domain.CommentVo;
import com.myaws.myapp.domain.SearchCriteria;

public interface CommentService {
	public ArrayList<CommentVo> commentSelectAll(int bidx);
	

	
}
