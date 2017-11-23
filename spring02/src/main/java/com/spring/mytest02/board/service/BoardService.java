package com.spring.mytest02.board.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.dao.DataAccessException;

import com.spring.mytest02.board.dto.BoardDTO;

public interface BoardService {
//	public ArrayList listBoard() throws DataAccessException;
	public HashMap listBoard() throws DataAccessException;
//	public BoardDTO writeDetail(int num) throws DataAccessException;
	public HashMap writeDetail(int num) throws DataAccessException;
	public BoardDTO modWrite(BoardDTO boardDTO) throws DataAccessException;
	public int addWrite(HashMap writeMap) throws DataAccessException;
	public void modWrite(HashMap writeMap) throws DataAccessException;
	public void delWrite(int num) throws DataAccessException;
}
