package com.myaws.myapp.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myaws.myapp.domain.BoardVo;
import com.myaws.myapp.domain.PageMaker;
import com.myaws.myapp.domain.SearchCriteria;
import com.myaws.myapp.service.BoardService;
import com.myaws.myapp.util.MediaUtils;
import com.myaws.myapp.util.UploadFileUtiles;
import com.myaws.myapp.util.UserIp;

@Controller
@RequestMapping(value = "/board/")
public class BoardController {

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	@Autowired(required = false) // 주입할 것
	private BoardService boardService;

	@Autowired(required = false)
	private PageMaker pm;

	@Autowired
	private UserIp userp;

	// @Autowired은 타입과 같은 것을 찾음
	@Resource(name = "uploadPath") // 객체의 참조변수 이름을 적어줘야하고, 이름과 같은 것을 찾음 > 빈으로 되어있는 것을 주입받음
	private String uploadPath;

	@RequestMapping(value = "boardList.aws", method = RequestMethod.GET)
	public String boardList(SearchCriteria scri, Model model) {

		logger.info("boardList �뱾�뼱�샂");

		int cnt = boardService.boardTotalCount(scri);
		// logger.info("boardList scri"+scri);
		// logger.info("boardList scri");
		// logger.info("boardList cnt"+cnt);

		pm.setScri(scri);
		pm.setTotalCount(cnt);
		// logger.info("boardList getScri"+pm.getScri());

		ArrayList<BoardVo> blist = boardService.boardSelectAll(scri);

		model.addAttribute("blist", blist);
		model.addAttribute("pm", pm);

		return "WEB-INF/board/boardList"; // .jsp는 WEB-INF/spring/appServlet/servlet-context.xml > 에서 붇어짐
	}

	@RequestMapping(value = "boardWrite.aws", method = RequestMethod.GET)
	public String boardWrite() {

		logger.info("boardWrite �뱾�뼱�샂");

		return "WEB-INF/board/boardWrite"; // .jsp는 WEB-INF/spring/appServlet/servlet-context.xml > 에서 붇어짐

	}

	@RequestMapping(value = "boardWriteAction.aws", method = RequestMethod.POST)
	public String boardWriteAction(BoardVo bv, // boardwrite 에 있는 파라미터를 BoardVo로 바인딩 해서 받음
			@RequestParam("attachfile") MultipartFile filename, // 파일 이름 넘겨 받기
			HttpSession session, // 로그인 정보 가져오기
			RedirectAttributes rttr, HttpServletRequest request) throws IOException, Exception { // UploadFileUtiles 오류시
																									// throws 될 입셉션
		logger.info("boardWriteAction 들어옴");

		MultipartFile file = filename; // 받은 파일 이름 담기
		String uploadedFileName = ""; // 업로드할 파일이름 변수 선언

		if (!file.getOriginalFilename().equals("")) { // 업로드 된 파일 이름이 없지 않다면
			uploadedFileName = UploadFileUtiles.uploadFile(uploadPath, file.getOriginalFilename(), file.getBytes());
			// IOException 오류가 나면 윗단계서 처리해줌 > 호출해주는 윗단계에서 처리함
			// util에 있는 UploadFileUtiles 클래스를 사용해서 저장위치, 파일이름, 파일크기를 넣어줌

		}

		String midx = session.getAttribute("midx").toString();
		int midx_int = Integer.parseInt(midx);
		String ip = userp.getUserIp(request);
		bv.setUploadedFilename(uploadedFileName);
		bv.setMidx(midx_int);
		bv.setIp(ip);

		int value = boardService.boardInsert(bv);

		String path = "";
		if (value == 2) {
			path = "redirect:/board/boardList.aws";
		} else {
			rttr.addFlashAttribute("msg", "입력이 잘못 되었습니다.");
			path = "redirect:/board/boardWrite.aws";
		}

		return path;

	}

	@RequestMapping(value = "boardContents.aws", method = RequestMethod.GET)
	public String boardContents(@RequestParam("bidx") int bidx, Model model) {

		logger.info("boardContents 들어옴");

		boardService.boardViewCntUpdate(bidx);
		BoardVo bv = boardService.boardSelectOne(bidx);

		model.addAttribute("bv", bv);

		return "WEB-INF/board/boardContents"; // .jsp는 WEB-INF/spring/appServlet/servlet-context.xml > 에서 붇어짐

	}

	@RequestMapping(value = "displayFile.aws", method = RequestMethod.GET)
	public ResponseEntity<byte[]> displayFile(@RequestParam("fileName") String fileName,
			@RequestParam(value = "down", defaultValue = "0") int down) {

		logger.info("displayFile 들어옴");

		ResponseEntity<byte[]> entity = null; // 바이트 배열을 여러개 넣는 리스트
		InputStream in = null; // 파일 읽음

		try {
			String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);
			MediaType mType = MediaUtils.getMediaType(formatName);

			HttpHeaders headers = new HttpHeaders();

			in = new FileInputStream(uploadPath + fileName);

			if (mType != null) {

				if (down == 1) {
					fileName = fileName.substring(fileName.indexOf("_") + 1);
					headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
					headers.add("Content-Disposition",
							"attachment; filename=\"" + new String(fileName.getBytes("UTF-8"), "ISO-8859-1") + "\"");

				} else {
					headers.setContentType(mType);
				}

			} else {

				fileName = fileName.substring(fileName.indexOf("_") + 1);
				headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
				headers.add("Content-Disposition",
						"attachment; filename=\"" + new String(fileName.getBytes("UTF-8"), "ISO-8859-1") + "\"");
			}
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.CREATED);

		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return entity;// .jsp는 WEB-INF/spring/appServlet/servlet-context.xml > 에서 붇어짐

	}

	@ResponseBody
	@RequestMapping(value = "boardRecom.aws", method = RequestMethod.GET)
	public JSONObject boardRecom(@RequestParam("bidx") int bidx) {

		int value = boardService.boardRecomUpdate(bidx);
		JSONObject js = new JSONObject();
		js.put("recom", value);

		return js; // .jsp는 WEB-INF/spring/appServlet/servlet-context.xml > 에서 붇어짐

	}

	@RequestMapping(value = "boardDelete.aws", method = RequestMethod.GET)
	public String boardDelete(@RequestParam("bidx") int bidx, Model model) {

		logger.info("boardDelete 들어옴");
		model.addAttribute("bidx", bidx);

		return "WEB-INF/board/boardDelete"; // .jsp는 WEB-INF/spring/appServlet/servlet-context.xml > 에서 붇어짐

	}

	@RequestMapping(value = "boardDelectAction.aws", method = RequestMethod.POST)
	public String boardDelectAction(@RequestParam("bidx") int bidx, @RequestParam("password") String password,
			RedirectAttributes rttr, HttpSession session) {

		logger.info("boardDelectAction 들어옴");

		BoardVo bv = boardService.boardSelectOne(bidx);
		int midx = Integer.parseInt(session.getAttribute("midx").toString());
		int value = boardService.boardDelete(bidx, midx, password);

		String path = "";
		if (bv.getMidx() == midx) {
			if (value == 1) {
				path = "redirect:/board/boardList.aws";
			} else {
				rttr.addFlashAttribute("msg", "비밀번호가 틀렸습니다.");
				path = "redirect:/board/boardDelete.aws?bidx=" + bidx;
			}
		} else {
			rttr.addFlashAttribute("msg", "글을 쓴 회원이 아닙니다.");
			path = "redirect:/board/boardDelete.aws?bidx=" + bidx;
		}

		return path; // .jsp는 WEB-INF/spring/appServlet/servlet-context.xml > 에서 붇어짐

	}

	@RequestMapping(value = "boardUpdate.aws", method = RequestMethod.GET)
	public String boardUpdate(@RequestParam("bidx") int bidx, Model model) {

		logger.info("boardUpdate 들어옴");
		BoardVo bv = boardService.boardSelectOne(bidx);

		model.addAttribute("bv", bv);

		return "WEB-INF/board/boardUpdate"; // .jsp는 WEB-INF/spring/appServlet/servlet-context.xml > 에서 붇어짐

	}

	@RequestMapping(value = "boardUpdateAction.aws", method = RequestMethod.POST)
	// 이 액션이 끝나면 성공하면 list, context로 이동, 실패하면 Update로 이동
	public String boardUpdateAction(BoardVo bv,
			// 파라미터로 하나씩 넘겨 받을 수 있지만
			// boardUpdate에 있는 파라미터를 BoardVo로 바인딩 해서 가져오는게 한번에 가져오고 편함
			@RequestParam("attachfile") MultipartFile filename,
			// attachfile 파라미터를 가지고오 이 타입은 MultipartFile 클래스의 filename변수명에 담아넣기
			HttpSession session, // 로그인 정보 가져오기
			RedirectAttributes rttr, HttpServletRequest request) throws IOException, Exception { // UploadFileUtiles 오류시
																									// throws 될 입셉션
		logger.info("boardUpdateAction 들어옴");

		MultipartFile file = filename; // 받은 파일 이름 담기
		String uploadedFileName = ""; // 업로드할 파일이름 변수 선언
		logger.info(" bv.getFilename()" + bv.getFilename());
		System.out.println(" filename" + filename);

		if (file == null && file.isEmpty() && bv.getFilename() != null && !bv.getFilename().isEmpty()) {
			bv.setUploadedFilename(bv.getFilename());
		} else if (file != null && !file.isEmpty() && bv.getFilename() != null && !bv.getFilename().isEmpty()) { // 업로드
																													// 된
																													// 파일
																													// 이름이
																													// 없지
																													// 않다면
			uploadedFileName = UploadFileUtiles.uploadFile(uploadPath, file.getOriginalFilename(), file.getBytes());
			// IOException 오류가 나면 윗단계서 처리해줌 > 호출해주는 윗단계에서 처리함
			// util에 있는 UploadFileUtiles 클래스를 사용해서 저장위치, 파일이름, 파일크기를 넣어줌
			bv.setUploadedFilename(uploadedFileName);

		} else if (file != null && !file.isEmpty() && bv.getFilename() == null && bv.getFilename().isEmpty()) { // 업로드 된
																												// 파일
																												// 이름이
																												// 없지
																												// 않다면
			uploadedFileName = UploadFileUtiles.uploadFile(uploadPath, file.getOriginalFilename(), file.getBytes());
			// IOException 오류가 나면 윗단계서 처리해줌 > 호출해주는 윗단계에서 처리함
			// util에 있는 UploadFileUtiles 클래스를 사용해서 저장위치, 파일이름, 파일크기를 넣어줌
			bv.setUploadedFilename(uploadedFileName);
		}

		String midx = session.getAttribute("midx").toString();
		int midx_int = Integer.parseInt(midx);
		String ip = userp.getUserIp(request);
		bv.setIp(ip);

		int value = boardService.boardUpdate(bv);

		String path = "";
		if (bv.getMidx() == midx_int) {
			if (value == 1) {
				path = "redirect:/board/boardList.aws";
			} else {
				rttr.addFlashAttribute("msg", "비밀번호가 틀렸습니다.");
				path = "redirect:/board/boardUpdate.aws?bidx=" + bv.getBidx();
			}
		} else {
			rttr.addFlashAttribute("msg", "글을 쓴 회원이 아닙니다.");
			path = "redirect:/board/boardUpdate.aws?bidx=" + bv.getBidx();
		}

		return path; // .jsp는 WEB-INF/spring/appServlet/servlet-context.xml > 에서 붇어짐

	}

	@RequestMapping(value = "boardReply.aws", method = RequestMethod.GET)
	public String boardReply(@RequestParam("bidx") int bidx, Model model) {

		logger.info("boardReply 들어옴");
		BoardVo bv = boardService.boardSelectOne(bidx);

		model.addAttribute("bv", bv);

		return "WEB-INF/board/boardReply"; // .jsp는 WEB-INF/spring/appServlet/servlet-context.xml > 에서 붇어짐

	}

	@RequestMapping(value = "boardReplyAction.aws", method = RequestMethod.POST)
	public String boardReplyAction(BoardVo bv,
			// 파라미터로 하나씩 넘겨 받을 수 있지만
			// boardUpdate에 있는 파라미터를 BoardVo로 바인딩 해서 가져오는게 한번에 가져오고 편함
			@RequestParam("attachfile") MultipartFile filename,
			// attachfile 파라미터를 가지고오 이 타입은 MultipartFile 클래스의 filename변수명에 담아넣기
			HttpSession session, // 로그인 정보 가져오기
			RedirectAttributes rttr, HttpServletRequest request) throws IOException, Exception { // UploadFileUtiles 오류시
																									// throws 될 입셉션
		logger.info("boardReplyAction 들어옴");

		MultipartFile file = filename; // 받은 파일 이름 담기
		String uploadedFileName = ""; // 업로드할 파일이름 변수 선언

		if (file != null && !file.isEmpty()) { // 업로드 된 파일 이름이 없지 않다면
			uploadedFileName = UploadFileUtiles.uploadFile(uploadPath, file.getOriginalFilename(), file.getBytes());
			// IOException 오류가 나면 윗단계서 처리해줌 > 호출해주는 윗단계에서 처리함
			// util에 있는 UploadFileUtiles 클래스를 사용해서 저장위치, 파일이름, 파일크기를 넣어줌

		}



		String midx = session.getAttribute("midx").toString();
		int midx_int = Integer.parseInt(midx);
		String ip = userp.getUserIp(request);
		bv.setUploadedFilename(uploadedFileName);
		bv.setMidx(midx_int);
		bv.setIp(ip);

		int maxBidx = boardService.boardReply(bv);
		System.out.println("BoardController boardReply maxBidx==> " + maxBidx);

		String path = "";

		if (maxBidx != 0) {
			path = "redirect:/board/boardContents.aws?bidx=" + maxBidx;
		} else {
			rttr.addFlashAttribute("msg", "답변이 등록하지 않았습니다.");
			path = "redirect:/board/boardReply.aws?bidx=" + bv.getBidx();
		}

		return path; // .jsp는 WEB-INF/spring/appServlet/servlet-context.xml > 에서 붇어짐

	}

}
