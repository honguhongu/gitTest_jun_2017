package com.spring.mytest02.common.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller()
public class FileDownloadController {
	@RequestMapping(value="/fileDownload.do", method= {RequestMethod.POST, RequestMethod.GET})
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			request.setCharacterEncoding("euc-kr");
			response.setContentType("text/html;charset=euc-kr");
			String num=request.getParameter("num");
			String file_repo="C:\\board_repo"+"\\"+num;
			String fileName=(String) request.getParameter("fileName");
			
			System.out.println("fileName="+fileName);
			OutputStream out =response.getOutputStream();
			String downFile=file_repo+"\\"+fileName;
			File f=new File(downFile);
			
			response.setHeader("Cache-Control","no-cache");
			response.addHeader("Content-disposition", "attachment); fileName="+fileName);
			FileInputStream in=new FileInputStream(f);
			byte[] buffer=new byte[1024*8];
			while(true) {
				int count=in.read(buffer);
				if(count==-1)
					break;
				out.write(buffer,0,count);
			}
			in.close();
			out.close();
		
		}catch(Exception e) {
			e.printStackTrace();
		}

	}
}
	

