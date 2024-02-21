package com.study.insert_and_select1.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.study.insert_and_select1.dao.StudentDAO;
import com.study.insert_and_select1.entity.Student;

/**
 * Servlet implementation class DataInsertServlet
 */
@WebServlet("/data/addition")
public class DataInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DataInsertServlet() {
		super();

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		StringBuilder builder = new StringBuilder();

		String readData = null;

		BufferedReader reader = request.getReader();

		/*
		 * "{ "name" : "김준일", "age" : 31 }"
		 */

		while ((readData = reader.readLine()) != null) {
			builder.append(readData);
		}

		// Json -> Map(키,밸류 형태)

		Gson gson = new Gson();
		Map<String, Object> map = gson.fromJson(builder.toString(), Map.class);

		System.out.println(map);
		System.out.println(map.get("name"));
		System.out.println(map.get("age"));

		// Json -> Entity 객체(키,밸류 형태)
		Student student = gson.fromJson(builder.toString(), Student.class);
		System.out.println(student);
		System.out.println(student.getName());
		System.out.println(student.getAge());

		StudentDAO studentDao = StudentDAO.getInstance();

		Student findStudent = studentDao.findStudentByName(student.getName());

		if (findStudent != null) {
			response.setStatus(400);
			Map<String, Object> errorMap = new HashMap<>();
			errorMap.put("errorMessage", "이미 등록된 이름입니다");
			response.setContentType("application/json");
			response.getWriter().println(gson.toJson(errorMap)); // json으로 빠져나간다
			return;
		}

		int successCount = studentDao.saveStudent(student);

		Map<String, Object> responseMap = new HashMap<>();
		responseMap.put("status", 201);
		responseMap.put("data", "응답 데이터");
		responseMap.put("successCount", successCount);

		response.setStatus(201);
		response.setContentType("application/json");

		PrintWriter writer = response.getWriter();
		writer.println(gson.toJson(responseMap));
	}

}
