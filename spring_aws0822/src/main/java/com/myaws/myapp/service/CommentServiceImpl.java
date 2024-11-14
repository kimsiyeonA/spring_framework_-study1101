package com.myaws.myapp.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myaws.myapp.domain.BoardVo;
import com.myaws.myapp.domain.CommentVo;
import com.myaws.myapp.domain.SearchCriteria;
import com.myaws.myapp.persistance.BoardMapper;
import com.myaws.myapp.persistance.CommentMapper;


@Service
public class CommentServiceImpl implements CommentService {


	private CommentMapper cm;
	
	@Autowired
	public CommentServiceImpl(SqlSession sqlSession) {
		this.cm = sqlSession.getMapper(CommentMapper.class); //()?븞?뿉 ??寃쎈줈瑜? ?엯?젰?빐?룄 ?맖
	}
	
	@Override
	public ArrayList<CommentVo> commentSelectAll(int bidx, int block) {
		// 햇시맵으로 넘길 수 있지만 자료형이 같은 경우에는 같이 넘길 수 있다.
		block=block*15; //0부터 리밋트 함수 쓰기
		ArrayList<CommentVo> clist = cm.commentSelectAll(bidx,block);
		return clist;
	}

	@Override
	public int commentInsert(CommentVo cv) {
		int value = cm.commentInsert(cv);
		return value;
	}

	@Override
	public int commentDelete(CommentVo cv) {
		int value = cm.commentDelete(cv);
		System.out.println("CommentServiceImpl commentDelete midx" + cv.getMidx());
		System.out.println("CommentServiceImpl commentDelete cidx" + cv.getCidx());
		System.out.println("CommentServiceImpl commentDelete Cip" + cv.getCip());
		System.out.println("CommentServiceImpl commentDelete Bidx" + cv.getBidx());
		System.out.println("CommentServiceImpl commentDelete value" + value);
		return value;
	}



	@Override
	public int commentTotalCut(int bidx) {
		int cnt = cm.commentTotalCut(bidx);
		return cnt;
	}

	
	
}
