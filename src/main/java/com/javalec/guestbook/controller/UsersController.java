package com.javalec.guestbook.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javalec.guestbook.service.IUsersService;
import com.javalec.guestbook.vo.UsersVO;

@Controller("users")
public class UsersController {
	@Autowired
	@Qualifier("usersservice")
	private IUsersService UsersService;

//	@RequestMapping("/getuserslist.do")
//	public String getUsersList(UsersDAO dao, UsersVO vo, Model model, HttpServletRequest req) {
//		System.out.println(" dddddddddd ");
//		String slt = req.getParameter("content");
//		if (slt != null) {
//			model.addAttribute("list", dao.getBook(vo));
////		} else {
//			model.addAttribute("list", UsersService.getUsersList());
////		}
//		return "index.jsp";
//	}

	@RequestMapping("/write.do")
	public String insertUsers(UsersVO vo) {
		System.out.println("입력컨트롤러");
		UsersService.insertUsers(vo);
		return "login.jsp";
	}
	
	@RequestMapping("/findpwd.do")
	public String selectUsers(UsersVO vo, HttpServletRequest req) {
		System.out.println("비번 컨트롤러");
		String id = req.getParameter("id");
		vo.setId(id);
		vo = UsersService.selectUsers(vo);
		String password = vo.getPassword();
		req.setAttribute("password", password);
		return "findpassword.jsp";
	}

//	@RequestMapping("/deleteform.do")
//	public String deleteformUsers() {
//		System.out.println("삭제폼컨트롤러");
//		return "deleteform.jsp";
//	}
//
//	@RequestMapping("/delete.do")
//	public String deleteUsers(UsersDAO dao, UsersVO vo) {
//		System.out.println("삭제컨트롤러");
//		UsersService.deleteUsers(vo);
//		return "getUserslist.do";
//	}
//
//	@RequestMapping("/update.do")
//	public String updateUsers(UsersDAO dao, UsersVO vo) {
//		System.out.println("수정컨트롤러");
//		UsersService.updateUsers(vo);
//		return "getUserslist.do";
//	}
//
	
	
	@RequestMapping("/login.do")
	public String loginUsers(UsersVO vo,  HttpSession session, Model model) {
		System.out.println("로긴컨트롤러");
		UsersVO result = UsersService.selectUsers(vo);		
		System.out.println(result.getId());
		System.out.println(result.getPassword());
		
		if(result.getId() == null) {
			model.addAttribute("msg", "아이디 또는 패스워드 오류");
			return "login.jsp";
		}
		
		if(result.getId().equals(vo.getId()) 
				&& result.getPassword().equals(vo.getPassword())){
			session.setAttribute("id", result.getId());
			return "getboardlist.do";
		}else {
			model.addAttribute("msg", "아이디 또는 패스워드 오류");
			return "login.jsp";
		}
}
	@RequestMapping("/logout.do")
	public String logoutUsers(HttpSession session) {
		session.getAttribute("id");	//id이름의 세션 가져오기
		System.out.println("session :" + session );
		if(session != null) {	//세션이 비어있지 않다면
			session.invalidate();	// 세션을 초기화
		}
		
		return "login.jsp";
	}
}
