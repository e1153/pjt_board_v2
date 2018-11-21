package com.javalec.guestbook.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javalec.guestbook.dao.BoardDAO;
import com.javalec.guestbook.vo.BoardVO;


@Service("boardservice")
public class BoardService implements IBoardService {
	
	
	public BoardService() {
		 		System.out.println("BoardService() call"); 
	}
	
	@Autowired
	private BoardDAO dao;
	String slt;
	
	@Override
	public int insertBoard(BoardVO vo) {
		System.out.println("입력컨트롤러");
		String title = vo.getTitle();
		String writer = vo.getWriter();
		String content = vo.getContent();
		vo.setTitle(title);
		vo.setWriter(writer);
		vo.setContent(content);
		vo.getId();
		System.out.println(vo.toString());
		return dao.insert(vo);
	}

	@Override
	public List<BoardVO> getBoardList() {
		// TODO Auto-generated method stub
		return dao.getBoardList();		
		
	}

	@Override
	public BoardVO getBoard(int no) {
		 dao.cntConunt(no);
		return dao.getBoard(no);		
		
	}
	
	
	
	@Override
	public int deleteBoard(int no) {
		return dao.delete(no);
	}

	@Override
	public int updateBoard(BoardVO vo) {
		
		
		return dao.update(vo);
	}

	@Override
	public List<BoardVO> STitle(String key) {
		// TODO Auto-generated method stub
		return dao.STitle(key);		
		
	}

	@Override
	public List<BoardVO> SContent(String key) {
		// TODO Auto-generated method stub
		return dao.SContent(key);	
		
	}
}
