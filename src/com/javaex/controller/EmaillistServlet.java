package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.EmaillistDao;
import com.javaex.vo.EmaillistVo;

@WebServlet("/el")
public class EmaillistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String actionName = request.getParameter("a");
		
		if("form".equals(actionName)) {
			System.out.println("form 진입");
			RequestDispatcher rd = request.getRequestDispatcher("form.jsp");
			rd.forward(request, response);
		}else if("insert".equals(actionName)) {
			System.out.println("insert 진입");
			
			String ln = request.getParameter("ln");
			String fn = request.getParameter("fn");
			String email = request.getParameter("email");
			
			EmaillistVo vo = new EmaillistVo(ln,fn,email);
			
			EmaillistDao dao = new EmaillistDao();
			
			dao.insert(vo);
			
			System.out.println(vo);
			
			response.sendRedirect("el?a=list");
			
		}else if("list".equals(actionName)) {
			System.out.println("list 진입");
			EmaillistDao dao = new EmaillistDao();
			List<EmaillistVo> list = dao.getlist();
			
			request.setAttribute("list", list);
			RequestDispatcher rd = request.getRequestDispatcher("list.jsp");
			rd.forward(request, response);
			
		}else {
			System.out.println("잘못된 a값처리 로직");
		}
		
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
