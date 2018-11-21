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
		System.out.println("�Է���Ʈ�ѷ�");
		UsersService.insertUsers(vo);
		return "login.jsp";
	}
	
	@RequestMapping("/findpwd.do")
	public String selectUsers(UsersVO vo, HttpServletRequest req) {
		System.out.println("��� ��Ʈ�ѷ�");
		String id = req.getParameter("id");
		vo.setId(id);
		vo = UsersService.selectUsers(vo);
		String password = vo.getPassword();
		req.setAttribute("password", password);
		return "findpassword.jsp";
	}

//	@RequestMapping("/deleteform.do")
//	public String deleteformUsers() {
//		System.out.println("��������Ʈ�ѷ�");
//		return "deleteform.jsp";
//	}
//
//	@RequestMapping("/delete.do")
//	public String deleteUsers(UsersDAO dao, UsersVO vo) {
//		System.out.println("������Ʈ�ѷ�");
//		UsersService.deleteUsers(vo);
//		return "getUserslist.do";
//	}
//
//	@RequestMapping("/update.do")
//	public String updateUsers(UsersDAO dao, UsersVO vo) {
//		System.out.println("������Ʈ�ѷ�");
//		UsersService.updateUsers(vo);
//		return "getUserslist.do";
//	}
//
	
	
	@RequestMapping("/login.do")
	public String loginUsers(UsersVO vo,  HttpSession session, Model model) {
		System.out.println("�α���Ʈ�ѷ�");
		UsersVO result = UsersService.selectUsers(vo);		
		System.out.println(result.getId());
		System.out.println(result.getPassword());
		
		if(result.getId() == null) {
			model.addAttribute("msg", "���̵� �Ǵ� �н����� ����");
			return "login.jsp";
		}
		
		if(result.getId().equals(vo.getId()) 
				&& result.getPassword().equals(vo.getPassword())){
			session.setAttribute("id", result.getId());
			return "getboardlist.do";
		}else {
			model.addAttribute("msg", "���̵� �Ǵ� �н����� ����");
			return "login.jsp";
		}
}
	@RequestMapping("/logout.do")
	public String logoutUsers(HttpSession session) {
		session.getAttribute("id");	//id�̸��� ���� ��������
		System.out.println("session :" + session );
		if(session != null) {	//������ ������� �ʴٸ�
			session.invalidate();	// ������ �ʱ�ȭ
		}
		
		return "login.jsp";
	}
}
