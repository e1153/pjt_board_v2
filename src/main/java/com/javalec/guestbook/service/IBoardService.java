package com.javalec.guestbook.service;

import java.util.List;

import com.javalec.guestbook.vo.BoardVO;


public interface IBoardService {
	
	public int insertBoard(BoardVO vo);
	public List<BoardVO> getBoardList();
	public List<BoardVO> STitle(String key);
	public List<BoardVO> SContent(String key);
	public int deleteBoard(int no);
	public int updateBoard(BoardVO vo);
	public BoardVO getBoard(int no);
	
}
