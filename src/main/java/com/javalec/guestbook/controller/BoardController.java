package com.javalec.guestbook.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javalec.guestbook.service.IBoardService;
import com.javalec.guestbook.vo.BoardVO;

@Controller("board")
public class BoardController {
	@Autowired
	@Qualifier("boardservice")
	private IBoardService BoardService;

	public BoardController() {
		// TODO Auto-generated constructor stub
		System.out.println("BoardController() call");
	}

	@RequestMapping("/getboardlist.do")
	public String getBoardList(BoardVO vo, Model model, HttpServletRequest req) {
		String key = req.getParameter("searchKeyword");
		String type = req.getParameter("searchCondition");
		System.out.println(key);
		System.out.println(type);
		
		if (key != null) {

			if (type.equals("content")) {
				model.addAttribute("list", BoardService.SContent(key));
			} else {
				model.addAttribute("list", BoardService.STitle(key));
			}
		} else {
			model.addAttribute("list", BoardService.getBoardList());
		}
		return "getBoardList.jsp";
	}

//		}

	@RequestMapping("/insert.do")
	public String insertBoard(BoardVO vo, HttpServletRequest req) {
		BoardService.insertBoard(vo);
		return "getboardlist.do";
	}

	@RequestMapping("/modifypage.do")
	public String modifyPage(HttpServletRequest req, Model model, BoardVO vo) {
		String stseq = (String) req.getParameter("seq");

		if (stseq == null || stseq.isEmpty()) {
			return "redirect:getboardlist.do";

		} else {
			int seq = Integer.parseInt(stseq);
			BoardVO result = BoardService.getBoard(seq);
			model.addAttribute("vo", result);
			return "modifyBoard.jsp";
		}

	}

	@RequestMapping("/update.do")
	public String updateBoard(BoardVO vo) {
		System.out.println("수정컨트롤러");
		BoardService.updateBoard(vo);
		return "getboardlist.do";
	}

	@RequestMapping("/delete.do")
	public String deleteBoard(HttpServletRequest req, Model model, BoardVO vo) {
		String stseq = (String) req.getParameter("seq");
		System.out.println(stseq);
		if (stseq == null || stseq.isEmpty()) {
			return "redirect:getboardlist.do";
		} else {
			int seq = Integer.parseInt(stseq);
			int result = BoardService.deleteBoard(seq);
			model.addAttribute("vo", result);
			return "getboardlist.do";
		}

	}

	@RequestMapping("/getboard.do")
	public String getBoard(BoardVO vo, HttpServletRequest req, Model model) {
		System.out.println("단건 조회 컨트롤러");
		BoardVO result = BoardService.getBoard(vo.getSeq());

		// req.setAttribute("vo", result);
		model.addAttribute("vo", result);
		return "getBoard.jsp";
	}
}
//
//
//	@RequestMapping("/updateform.do")
//	public String updateformBoard() {
//		System.out.println("수정폼컨트롤러");
//		return "updateform.jsp";
//	}
//
//	@RequestMapping("/loginform.do")
//	public String loginformBoard() {
//		System.out.println("로긴폼컨트롤러");
//		return "loginform.jsp";
//
//	}
//	
//
//
//	@RequestMapping("/login.do")
//	public String loginBoard(HttpServletRequest request, HttpServletResponse response) {
//		System.out.println("로긴컨트롤러");
//		String name = request.getParameter("name");
//		String password = request.getParameter("password");
//
//		HttpSession session = request.getSession();
//		session.setAttribute("name", name);
//		session.setAttribute("password", password);
//
//		return "getBoardlist.do";
//		
//	}
//	@RequestMapping("/logout.do")
//	public String logoutBoard(HttpServletRequest request, HttpServletResponse response) {
//		HttpSession session = request.getSession();
//		session.invalidate();
//		
//		return "getBoardlist.do";
//	}
//}
