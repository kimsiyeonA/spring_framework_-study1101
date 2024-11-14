package com.myaws.myapp.controller;

import java.net.InetAddress;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.myaws.myapp.domain.CommentVo;
import com.myaws.myapp.service.CommentService;
import com.myaws.myapp.util.UserIp;

@RestController // @ResponseBody가 있는 것과 같다.
@RequestMapping(value = "/comment")
public class CommentController {

	@Autowired
	CommentService commentService;

	@Autowired
	private UserIp userp;

	
	// @ResponseBody// 객체를 받기 위한 것 > josn은 객체이기 때문에
	@RequestMapping(value = "/{bidx}/{block}/commentList.aws") // {bidx}를 @PathVariable로 꺼냄 / 자원 리소스
	public JSONObject conmmemtList(@PathVariable("bidx") int bidx,@PathVariable("block") int block) {

		System.out.println("CommentController commentList  bidx " +bidx);
		System.out.println("CommentController commentList  block " +block);
		JSONObject js = new JSONObject();

		String moreView="";
		
		int cut = commentService.commentTotalCut(bidx); // 댓글 총 개수
		int nextblock=0;
		if(cut>block*15) {
			moreView="Y"; //  더보기 버튼이 나타나게 함
			nextblock = block+1;
			
		}else {
			 moreView="N";
			 nextblock = block;
		}
		
		System.out.println("CommentController commentList  cut " +cut);
		System.out.println("CommentController commentList  block2 " +block);
		
		
		ArrayList<CommentVo> clist = commentService.commentSelectAll(bidx,block);
		
		System.out.println("CommentController commentList  nextblock " +nextblock);
		System.out.println("CommentController commentList  moreView " +moreView);
		
		js.put("clist", clist);
		js.put("moreView", moreView);
		js.put("nextblock", nextblock);

		
		return js;

	}

	@RequestMapping(value = "/commentWriteAction.aws", method = RequestMethod.POST)
	public JSONObject commentWriteAction(CommentVo cv, HttpServletRequest request) throws Exception {

		System.out.println("CommentController commentWriteAction cwriter" + cv.getCwriter());
		System.out.println("CommentController commentWriteAction ccontents" + cv.getCcontents());
		System.out.println("CommentController commentWriteAction bidx" + cv.getBidx());
		System.out.println("CommentController commentWriteAction midx" + cv.getMidx());


		cv.setCip(userp.getUserIp(request));
		
		JSONObject js = new JSONObject();
		
		
		int value = commentService.commentInsert(cv);
		System.out.println("CommentController commentWriteAction value" + value);
		js.put("value", value);
		 
		
		return js;

	}
	
	@RequestMapping(value = "/{cidx}/commentDeleteAction.aws", method = RequestMethod.GET)
	public JSONObject commentDeleteAction(
			@PathVariable("cidx") int cidx,
			HttpServletRequest request,
			CommentVo cv
			) throws Exception {

		System.out.println("CommentController commentDeleteAction cidx" + cidx);
		

		int midx = Integer.parseInt(request.getSession().getAttribute("midx").toString());
		cv.setMidx(midx);
		cv.setCidx(cidx);
		cv.setCip(userp.getUserIp(request));
		System.out.println("CommentController /commentDeleteAction midx" + midx);
		System.out.println("CommentController /commentDeleteAction cidx" + cidx);
		System.out.println("CommentController /commentDeleteAction userp.getUserIp(request)" + userp.getUserIp(request));
		
		JSONObject js = new JSONObject();
		
		int value = commentService.commentDelete(cv);	
		System.out.println("CommentController /commentDeleteAction value" + value);
		
		js.put("value", value);
		 
		
		return js;

	}
	

}
