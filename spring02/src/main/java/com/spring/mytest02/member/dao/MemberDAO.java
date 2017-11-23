package com.spring.mytest02.member.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.spring.mytest02.member.dto.MemberDTO;

public interface MemberDAO {
	public List selectList() throws DataAccessException;
	public boolean overlapped(String id) throws DataAccessException;
	public void addMember(MemberDTO memberDTO) throws DataAccessException;
	public MemberDTO detail(String id) throws DataAccessException;
	public void modMember(MemberDTO memberDTO) throws DataAccessException;
	public void delMember(String id) throws DataAccessException;
	public boolean login(MemberDTO memberDTO) throws DataAccessException;

}
