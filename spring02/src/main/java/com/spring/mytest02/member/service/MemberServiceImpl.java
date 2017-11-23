package com.spring.mytest02.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.spring.mytest02.member.dao.MemberDAO;
import com.spring.mytest02.member.dto.MemberDTO;

@Service("memberService")
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	MemberDAO memberDAO;
	
	public List selectList() throws DataAccessException{
		return memberDAO.selectList();
		
	}
	
	public boolean overlapped(String id) throws DataAccessException{
		
		return memberDAO.overlapped(id);
	}

	public void addMember(MemberDTO memberDTO) throws DataAccessException{
		memberDAO.addMember(memberDTO);
	}
	
	public MemberDTO detail(String id) throws DataAccessException{
		return memberDAO.detail(id);
	}
	
	public void modMember(MemberDTO memberDTO) throws DataAccessException{
		memberDAO.modMember(memberDTO);
	}
	
	public void delMember(String id) throws DataAccessException{
		memberDAO.delMember(id);
	}
	
	public boolean login(MemberDTO memberDTO) throws DataAccessException{
		return memberDAO.login(memberDTO);
	}
}
