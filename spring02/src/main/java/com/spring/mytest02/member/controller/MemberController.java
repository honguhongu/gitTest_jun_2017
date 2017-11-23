package com.spring.mytest02.member.controller;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.spring.mytest02.member.dto.MemberDTO;
import com.spring.mytest02.member.service.MemberService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class MemberController extends MultiActionController{
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	@Autowired
	private MemberService memberService;
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/member/listMember.do", method = RequestMethod.GET)
	public ModelAndView listMember(HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		//String message=request.getParameter("message");
		//System.out.println("message:"+message);
		ModelAndView mav=new ModelAndView("/member/listMember");
		List memList=memberService.selectList();
		mav.addObject("memList", memList);
		return mav;
	}
	
	@RequestMapping(value = "/member/memberForm.do", method = RequestMethod.GET)
	public ModelAndView memberForm(HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		ModelAndView mav=new ModelAndView("/member/memberForm");
		return mav;
	}
	
	@RequestMapping(value = "/member/overlapped.do", method = RequestMethod.GET)
	public ModelAndView overlapped(HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		System.out.println("overlapped 메소드 호출");
		String id=request.getParameter("id");
		boolean result=memberService.overlapped(id);
		PrintWriter pw=response.getWriter();
		pw.print(result);
		//ModelAndView mav=new ModelAndView("/member/memberForm");
		return null;
	}
	
	@RequestMapping(value = "/member/addMember.do", method = RequestMethod.POST)
	public ModelAndView addMember(HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		System.out.println("addMember 메소드 호출");
		//request.setCharacterEncoding("euc-kr");
		String id=request.getParameter("id");
		String name=request.getParameter("name");
		String _weight=request.getParameter("weight");
		String _height=request.getParameter("height");
		String _age=request.getParameter("age");
		
		int weight=Integer.parseInt(_weight);
		int height=Integer.parseInt(_height);
		int age=Integer.parseInt(_age);
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setId(id);
		memberDTO.setName(name);
		memberDTO.setWeight(weight);
		memberDTO.setHeight(height);
		memberDTO.setAge(age);
		
		memberService.addMember(memberDTO);
		
		
		ModelAndView mav=new ModelAndView("redirect:listMember.do");
		return mav;
	}
	
	@RequestMapping(value = "/member/detail.do", method = RequestMethod.GET)
	public ModelAndView detail(HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		System.out.println("detail 메소드 호출");
		//request.setCharacterEncoding("euc-kr");
		String id=request.getParameter("id");
		MemberDTO memberDTO = memberService.detail(id);
		
		ModelAndView mav=new ModelAndView("/member/detail");
		mav.addObject("detail", memberDTO);
		return mav;
	}
	
	@RequestMapping(value = "/member/modMember.do", method = RequestMethod.POST)
	public ModelAndView modMember(HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		System.out.println("addMember 메소드 호출");
		//request.setCharacterEncoding("euc-kr");
		String id=request.getParameter("id");
		String name=request.getParameter("name");
		String _weight=request.getParameter("weight");
		String _height=request.getParameter("height");
		String _age=request.getParameter("age");
		
		int weight=Integer.parseInt(_weight);
		int height=Integer.parseInt(_height);
		int age=Integer.parseInt(_age);
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setId(id);
		memberDTO.setName(name);
		memberDTO.setWeight(weight);
		memberDTO.setHeight(height);
		memberDTO.setAge(age);
		
		memberService.modMember(memberDTO);
		
		
		ModelAndView mav=new ModelAndView("redirect:listMember.do");
		return mav;
	}
	
	@RequestMapping(value = "/member/delMember.do", method = RequestMethod.GET)
	public ModelAndView delMember(HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		System.out.println("delMember 메소드 호출");
		//request.setCharacterEncoding("euc-kr");
		String id = request.getParameter("id");
		
		memberService.delMember(id);
		
		ModelAndView mav=new ModelAndView("redirect:listMember.do");
		return mav;
	}
	
	 @RequestMapping(value="/member/login.do",method=RequestMethod.POST)
	 public  ModelAndView login(HttpServletRequest request, HttpServletResponse response)
	            throws Exception{
		  System.out.println("login 메소드 호출");
		  //String id=request.getParameter("id");
		  //String passwd=request.getParameter("passwd");
		  MemberDTO memberDTO=new MemberDTO();
		  bind(request,memberDTO);
		  boolean result=memberService.login(memberDTO);
		  ModelAndView mav=new ModelAndView("redirect:/board/listBoard.do");
		  if(result==true){
		   HttpSession session=request.getSession();
		   session.setAttribute("isLogOn",new Boolean(true));
		   session.setAttribute("id", memberDTO.getId());   
		  	}else{
		  		mav.setViewName("redirect:loginForm.do");
	  	}
		return mav;
	}
	 
	 @RequestMapping(value="/member/logout.do",method=RequestMethod.GET)
	 public  ModelAndView logout(HttpServletRequest request, HttpServletResponse response)
	            throws Exception{
		  System.out.println("logout 메소드 호출");
		  //String id=request.getParameter("id");
		  //String passwd=request.getParameter("passwd");
		  HttpSession session=request.getSession();
		  session.setAttribute("isLogOn",new Boolean(false));
		  session.removeAttribute("id");
		  ModelAndView mav=new ModelAndView("redirect:/board/listBoard.do");
		  return mav;
	} 
	
	@RequestMapping(value = "/member/loginForm.do", method = RequestMethod.GET)
	public ModelAndView loginForm(HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		System.out.println("loginForm 메소드 호출");
		ModelAndView mav=new ModelAndView("/member/loginForm");
		return mav;
	}
	
	
	
}
