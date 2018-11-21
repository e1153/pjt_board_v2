package com.javalec.guestbook.service;


import com.javalec.guestbook.vo.UsersVO;

public interface IUsersService {
	
	public void insertUsers(UsersVO vo);
//	public void deleteUsers(UsersVO vo);
//	public List<UsersVO> getUsersList();
//	public void updateUsers(UsersVO vo);
	public UsersVO selectUsers(UsersVO vo);
//	public UsersVO loginUsers(UsersVO vo);
	
}
