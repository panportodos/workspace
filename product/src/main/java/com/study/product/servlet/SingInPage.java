package com.study.product.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.product.dto.UserDto;

/**
 * Servlet implementation class SingInPage
 */
@WebServlet("/signin.do")
public class SingInPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SingInPage() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {// 페이지를 요청하는거니까 무조건 get요청

		UserDto dbUser = UserDto.builder().username("test").password("1234").name("김준일").email("24332@gmail.com")
				.build();

		request.getServletContext().setAttribute("dbUser", dbUser); // 전역저장소

		request.getRequestDispatcher("/WEB-INF/views/signin.html").forward(request, response);
	}

}
