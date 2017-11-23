package com.spring.mytest02.board.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.spring.mytest02.board.dao.BoardDAO;
import com.spring.mytest02.board.dto.BoardDTO;

@Service("boardService")
public class BoardServiceImpl implements BoardService{
	@Autowired
	BoardDAO boardDAO;
//	public ArrayList listBoard() throws DataAccessException{
	public HashMap listBoard() throws DataAccessException{
		ArrayList public_list=boardDAO.listBoard("y");
		ArrayList private_list=boardDAO.listBoard("n");
		HashMap map=new HashMap();
		map.put("public_list", public_list);
		map.put("private_list", private_list);
//		ArrayList boardList=boardDAO.listBoard();
		return map;
	}
	
	public HashMap writeDetail(int num) throws DataAccessException{
		HashMap writeMap=new HashMap();
		BoardDTO boardDTO=boardDAO.writeDetail(num);
		ArrayList imageList=boardDAO.listImageFile(num);
		writeMap.put("write", boardDTO);
		writeMap.put("imageList", imageList);
		return writeMap;
	}
	
	public BoardDTO modWrite(BoardDTO boardDTO) throws DataAccessException{
		
		return boardDAO.modWrite(boardDTO);
	}
	
	public int addWrite(HashMap writeMap) throws DataAccessException{
		int num=boardDAO.getPrimaryKey();
		writeMap.put("num", num);
		boardDAO.addWrite(writeMap);
		//이미지 정보를 t_image에 추가한다.
		ArrayList fileList=(ArrayList)writeMap.get("fileList");
		boardDAO.addImageFile(fileList, num);
		return num;
	}
	
	public void modWrite(HashMap writeMap) throws DataAccessException{
		boardDAO.modWrite(writeMap);
	}
	
	public void delWrite(int num) throws DataAccessException{
		boardDAO.delWrite(num);
	}

}
