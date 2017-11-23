package com.spring.mytest02.board.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.dao.DataAccessException;

import com.spring.mytest02.board.dto.BoardDTO;

public interface BoardDAO {
//	public ArrayList listBoard() throws DataAccessException;
	public ArrayList listBoard(String publicWrite) throws DataAccessException;
	public BoardDTO writeDetail(int num) throws DataAccessException;
	public BoardDTO modWrite(BoardDTO boardDTO) throws DataAccessException;
	public void addWrite(HashMap writeMap) throws DataAccessException;
	public int getPrimaryKey() throws DataAccessException;
	public void modWrite(HashMap writeMap) throws DataAccessException;
	public void delWrite(int num) throws DataAccessException;
	public void addImageFile(ArrayList fileList, int num) throws DataAccessException;
	public ArrayList listImageFile(int num) throws DataAccessException;
}
