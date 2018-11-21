package com.javalec.guestbook.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javalec.guestbook.dao.UsersDAO;
import com.javalec.guestbook.vo.UsersVO;

@Service("usersservice")
public class UsersService implements IUsersService {
	@Autowired
	private UsersDAO dao;
	String slt;
	
	@Override
	public void insertUsers(UsersVO vo) {
		// TODO Auto-generated method stub
		dao.insert(vo);
	}

	@Override
	public UsersVO selectUsers(UsersVO vo) {
		// TODO Auto-generated method stub
		return dao.selectUser(vo);
	}
//	@Override
//	public UsersVO loginUsers(UsersVO vo) {
//		return dao.login(vo);
//	}

//	@Override
//	public List<UsersVO> getUsersList() {
//		// TODO Auto-generated method stub
//		return dao.getBookList();		
//		
//	}
//	@Override
//	public void updateUsers(UsersVO vo) {
//		// TODO Auto-generated method stub
//		dao.update(vo);
//	}

//	@Override
//	public void deleteUsers(UsersVO vo) {
//		// TODO Auto-generated method stub
//		dao.delete(vo);
//	}

}
