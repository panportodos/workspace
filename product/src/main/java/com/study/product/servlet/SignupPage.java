package com.study.product.servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/signup.do")
public class SignupPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SignupPage() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ServletContext application = request.getServletContext();
		application.setAttribute("key4", "value1");
		application.setAttribute("key2", "value2");
		application.setAttribute("key3", "value3");

		request.setAttribute("key4", "value4");
		request.setAttribute("key5", "value5");
		request.setAttribute("key6", "value6");

		HttpSession session = request.getSession();
		session.setAttribute("key4", "value7");
		session.setAttribute("key8", "value8");
		session.setAttribute("key9", "value9");

		request.getRequestDispatcher("/WEB-INF/views/signup.jsp").forward(request, response); // servlet으로 넘어온 request를
																								// jsp로 넘겨준다
																								// request 객체에다가 key밸류로
																								// 저장을 한다
	}

}
