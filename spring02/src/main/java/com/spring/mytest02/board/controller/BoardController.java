package com.spring.mytest02.board.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.spring.mytest02.HomeController;
import com.spring.mytest02.board.dto.BoardDTO;
import com.spring.mytest02.board.service.BoardService;


@Controller
public class BoardController extends MultiActionController {
	private static String IMAGE_DIR="c:\\board_repo\\";
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	@Autowired
	BoardService boardService;
	
	@RequestMapping(value = "/board/listBoard.do", method = RequestMethod.GET)
	public ModelAndView listBoard(HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		System.out.println("listBoard 메서드 호출");
		//ArrayList boardList=boardService.listBoard();
		HashMap map=boardService.listBoard();
		ModelAndView mav=new ModelAndView("/board/listBoard");
//		mav.addObject("boardList", boardList);
		mav.addObject("map", map);
		return mav;
	}
	
	@RequestMapping(value = "/board/writeDetail.do", method = RequestMethod.GET)
	public ModelAndView writeDetail(HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		System.out.println("writeDetail 메서드 호출");
		String _num=request.getParameter("num");
		int num=Integer.parseInt(_num);
		System.out.println("num="+num);
		HashMap map=boardService.writeDetail(num);
	
		
		ModelAndView mav=new ModelAndView("/board/writeDetail");
		mav.addObject("map", map);
//		mav.addObject("write", boardDTO);
		return mav;
	}
	
	@RequestMapping(value = "/board/modWrite.do", method = RequestMethod.POST)
	public ModelAndView modWrite(MultipartHttpServletRequest multipartRequest, HttpServletResponse response)
			throws Exception{
		System.out.println("modWrite 메서드 호출");
		HashMap writeMap=new HashMap();
		writeMap=modWriteFileUpload(multipartRequest);
		boardService.modWrite(writeMap);
		//수정시 업로드된 이미지를 글번호 폴더로 이동한다.
		String imageFile=(String)writeMap.get("imageFile");
		String num=(String)writeMap.get("num");
		String oldImageFile=(String)writeMap.get("oldImageFile");
		File srcFile=new File(IMAGE_DIR+imageFile);
		File destDir=new File(IMAGE_DIR+num);
		FileUtils.copyFileToDirectory(srcFile, destDir, true);
		srcFile.delete();
		
		//기존의 이미지를 삭제한다.
		File oldImage=new File(IMAGE_DIR+num+"\\"+oldImageFile);
		oldImage.delete();
		
		ModelAndView mav=new ModelAndView("redirect:writeDetail.do");
		mav.addObject("num", num);
		return mav;
		
		
	}
	
	@RequestMapping(value = "/board/addWrite.do", method = RequestMethod.POST	)
	public ModelAndView addWrite(MultipartHttpServletRequest multipartRequest, HttpServletResponse response)
			throws Exception{
		HashMap writeMap=new HashMap();
		writeMap=addNewFileUpload(multipartRequest);
		HttpSession session = multipartRequest.getSession();
		String id=(String)session.getAttribute("id");
		writeMap.put("id", id);
		
		int num=boardService.addWrite(writeMap);
		
		//String imageFile=(String)writeMap.get("imageFile");
		
		ArrayList fileList=(ArrayList)writeMap.get("fileList");
		for(int i=0 ; i<fileList.size(); i++) {
			String imageFile=(String)fileList.get(i);
			File srcFile=new File(IMAGE_DIR+imageFile);
			File destDir=new File(IMAGE_DIR+num);
			FileUtils.copyFileToDirectory(srcFile, destDir, true);
			srcFile.delete();
		}
		
		ModelAndView mav=new ModelAndView("redirect:listBoard.do");
		return mav;
	}
	
	@RequestMapping(value = "/board/writeForm.do", method = RequestMethod.GET)
	public ModelAndView writeForm(HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		System.out.println("writeForm 메서드 호출");
		
		ModelAndView mav=new ModelAndView("/board/writeForm");
		return mav;
	}
	
	@RequestMapping(value = "/board/delWrite.do", method = RequestMethod.GET)
	public ModelAndView delWrite(HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		System.out.println("delWrite 메서드 호출");
		String _num=request.getParameter("num");
		int num=Integer.parseInt(_num);
		boardService.delWrite(num);
		//글 삭제할때 이미지도 지울거니까 폴더를 날려버리자
		File destDir=new File(IMAGE_DIR+num);
		FileUtils.deleteDirectory(destDir);
		ModelAndView mav=new ModelAndView("redirect:listBoard.do");
		return mav;
	}
	
	private HashMap addNewFileUpload(MultipartHttpServletRequest multipartRequest)
								throws Exception{
		
		HashMap writeMap=new HashMap();
		ArrayList fileList=new ArrayList();
		String path="c:\\board_repo\\";
		System.out.println("write 메서드 호출");
		Enumeration enu=multipartRequest.getParameterNames();
		while(enu.hasMoreElements()) {
			String key=(String)enu.nextElement();
			String value=multipartRequest.getParameter(key);
			System.out.println("key="+key+ " ,value"+value);
			writeMap.put(key, value);
		}
		
		Iterator<String> fileNames=multipartRequest.getFileNames();
		while(fileNames.hasNext()){
			String fileName=fileNames.next();
			System.out.println("fileName="+fileName);
			MultipartFile mFile=multipartRequest.getFile(fileName);
			String originaleFileName=mFile.getOriginalFilename();
			System.out.println("originaleFileName="+originaleFileName);
			
			//이미지를 안넣거나 중간에 안넣거나 하는경우에는 컨티뉴를 통해서 와일문을 다시 돌리면 추가한 사진만 추가할 수 있게됨. 
			if(originaleFileName==null || originaleFileName.length()==0) {
				continue;
			}
			//이미지파일을 여러개 넣기위해 어레이리스트를 사용한다. 
			//writeMap.put("imageFile", originaleFileName);기존에 사용할떈 이렇게 put 해서 하나만 넣었음
			fileList.add(originaleFileName);
			File file=new File(path+fileName);
			
			if(mFile.getSize() != 0) {
				if(!file.getParentFile().mkdirs()) {
					file.createNewFile();
				}
			}
			mFile.transferTo(new File(path+originaleFileName));
		}//end while
		
		writeMap.put("fileList", fileList);
		return writeMap;
	}
	
	private HashMap modWriteFileUpload(MultipartHttpServletRequest multipartRequest)
			throws Exception{

		HashMap writeMap=new HashMap();
		String path="c:\\board_repo\\";
		System.out.println("write 메서드 호출");
		Enumeration enu=multipartRequest.getParameterNames();
		while(enu.hasMoreElements()) {
			String key=(String)enu.nextElement();
			String value=multipartRequest.getParameter(key);
			System.out.println("key="+key+ " ,value"+value);
			writeMap.put(key, value);
		}
		
		Iterator<String> fileNames=multipartRequest.getFileNames();
		
		while(fileNames.hasNext()){
			String fileName=fileNames.next();
			System.out.println("fileName="+fileName);
			MultipartFile mFile=multipartRequest.getFile(fileName);
			String originaleFileName=mFile.getOriginalFilename();
			writeMap.put("imageFile", originaleFileName);
			File file=new File(path+fileName);
		
		if(mFile.getSize() != 0) {
			if(!file.getParentFile().mkdirs()) {
				file.createNewFile();
			}
		}
		mFile.transferTo(new File(path+originaleFileName));
		}//end while
		return writeMap;
	}
}
