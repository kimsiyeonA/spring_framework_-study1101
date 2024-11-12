package com.myaws.myapp.controller;

import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.myaws.myapp.domain.CommentVo;
import com.myaws.myapp.service.CommentService;


@RestController // @ResponseBody가 있는 것과 같다.
@RequestMapping(value="/comment")
public class CommentController {
	
	@Autowired
	CommentService commentService;
	
	//@ResponseBody// 객체를 받기 위한 것 > josn은 객체이기 때문에
	@RequestMapping(value="/{bidx}/commentList.aws") // {bidx}를 @PathVariable로 꺼냄 / 자원 리소스
	public JSONObject conmmemtList(@PathVariable("bidx") int bidx){
		
		JSONObject js =new JSONObject();
		
		ArrayList<CommentVo> clist = commentService.commentSelectAll(bidx);
		js.put("clist",clist);
		
		
		return js;
	
	}
}
