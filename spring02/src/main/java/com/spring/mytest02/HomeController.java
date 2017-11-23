package com.spring.mytest02;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController extends MultiActionController{
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	@Autowired
	private SqlSession sqlSession;
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
/*	@RequestMapping(value = "/member/listMember.do", method = RequestMethod.GET)
	public ModelAndView listMember(HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		//String message=request.getParameter("message");
		//System.out.println("message:"+message);
		ModelAndView mav=new ModelAndView("/member/listMember");
		List memList=sqlSession.selectList("member.selectAllMemberList");
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
		String temp=sqlSession.selectOne("member.overlapped",id);
		boolean result=Boolean.parseBoolean(temp);
		
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
		
		sqlSession.insert("member.addMember",memberDTO);
		
		ModelAndView mav=new ModelAndView("redirect:listMember.do");
		return mav;
	}
	
	@RequestMapping(value = "/member/detail.do", method = RequestMethod.GET)
	public ModelAndView detail(HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		System.out.println("detail 메소드 호출");
		//request.setCharacterEncoding("euc-kr");
		String id=request.getParameter("id");
		MemberDTO memberDTO=(MemberDTO)sqlSession.selectOne("member.detail",id);
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
		
		sqlSession.update("member.modMember",memberDTO);
		
		ModelAndView mav=new ModelAndView("redirect:listMember.do");
		return mav;
	}
	
	@RequestMapping(value = "/member/delMember.do", method = RequestMethod.GET)
	public ModelAndView delMember(HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		System.out.println("delMember 메소드 호출");
		//request.setCharacterEncoding("euc-kr");
		String id=request.getParameter("id");
		sqlSession.delete("member.delMember",id);
		ModelAndView mav=new ModelAndView("redirect:listMember.do");
		return mav;
	}
	
	*/
}
