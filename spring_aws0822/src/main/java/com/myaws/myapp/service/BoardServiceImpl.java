package com.myaws.myapp.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myaws.myapp.domain.BoardVo;
import com.myaws.myapp.domain.SearchCriteria;
import com.myaws.myapp.persistance.BoardMapper;


@Service
public class BoardServiceImpl implements BoardService {


	private BoardMapper bm;
	
	@Autowired
	public BoardServiceImpl(SqlSession sqlSession) {
		this.bm = sqlSession.getMapper(BoardMapper.class); //()안에 풀경로를 입력해도 됨
	}
	
	@Override
	public ArrayList<BoardVo> boardSelectAll(SearchCriteria scri) {
		HashMap<String,Object> hm = new HashMap<String,Object>();
		hm.put("perPageNum", scri.getPerPageNum());
		hm.put("searchType", scri.getSearchType());
		hm.put("keyword", scri.getKeyword());	
		hm.put("startPageNum", (scri.getPage()-1)*scri.getPerPageNum());
		
		ArrayList<BoardVo> blist = bm.boardSelectAll(hm);
		return blist;
	}

	@Override
	public int boardTotalCount(SearchCriteria scri) {

		
		int cnt = bm.boardTotalCount(scri);
		
		return cnt;
	}

	@Override
	@Transactional
	public int boardInsert(BoardVo bv) {
		
		int value = bm.boardInsert(bv);
		int maxBidx = bv.getBidx(); //업데이트 한 것을 넣음		
		int value2 = bm.boardOriginbidxUpdate(maxBidx);

		return value + value2; // 성공하면 2가 나옴
	}

	@Override
	public BoardVo boardSelectOne(int bidx) {
		BoardVo bv = bm.boardSelectOne(bidx);
		return bv;
	}

	@Override
	public int boardViewCntUpdate(int bidx) {
		int value = bm.boardViewCntUpdate(bidx);
		return value;
	}

	@Override
	public int boardRecomUpdate(int bidx) {
		
		BoardVo bv = new BoardVo();
		bv.setBidx(bidx);
		bm.boardRecomUpdate(bv);
		int recom = bv.getRecom();
		return recom;
	}

	@Override
	public int boardDelete(int bidx, int midx, String password) {
		
		HashMap<String, Object> hm = new HashMap<String, Object>();
		hm.put("bidx", bidx);
		hm.put("midx", midx);
		hm.put("password", password);
		
		int cut = bm.boardDelete(hm);
		
		return cut;
	}

	@Override
	public int boardUpdate(BoardVo bv) {
		int cut = bm.boardUpdate(bv);
		//System.out.println("BoardServiceImpl boardUpdate cut"+cut);
		return cut;
	}

	// 하나의 작은 그룹으로 만들어서 실행
	// 하나라도 실패하면 원상복귀, 둘다 성공하면 실행
	@Transactional
	@Override
	public int boardReply(BoardVo bv) {
		int value = bm.boardReplyUpdate(bv);
		//BoardServiceImpl boardReply value==> 2-3은 이후 값이 업데이트 되었다는 뜻
		System.out.println("BoardServiceImpl boardReply value==> "+value);
		int value2 = bm.boardReplyInsert(bv);
		System.out.println("BoardServiceImpl boardReply value2==> "+value2);
		int maxBidx = bv.getBidx();
		System.out.println("BoardServiceImpl boardReply maxBidx==> "+maxBidx);
		return maxBidx;
	}
	
}
