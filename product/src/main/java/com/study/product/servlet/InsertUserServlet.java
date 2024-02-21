package com.study.product.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.product.dto.InsertUserReqDto;
import com.study.product.utils.RequestUtil;
import com.study.product.utils.ResponseEntity;

@WebServlet("/user")
public class InsertUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public InsertUserServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		InsertUserReqDto dto = RequestUtil.convertJsonData(request, InsertUserReqDto.class);
		System.out.println(dto);
		ResponseEntity.ofJson(response, 200, dto);
		// 아래 코드로 간단해진다
//			public static <T> T convertJsonData(HttpServletRequest request, Class<T> classofT) throws IOException {
//				// public String void test() 와 같은 구조 / <T> 제네릭 , T 리턴값
//				String requestJsonData = null;
//				StringBuilder builder = new StringBuilder();
//				String readLineData = null;
//				BufferedReader reader = request.getReader();
//
//				while ((readLineData = reader.readLine()) != null) {
//					builder.append(readLineData);
//				}
//				requestJsonData = builder.toString();
//
//				Gson gson = new Gson();
//
//				return gson.fromJson(requestJsonData, classofT);
//
//			}
	}

}
