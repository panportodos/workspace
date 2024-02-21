package com.study.product.utils;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;

public class RequestUtil {

	public static String getJsonData(HttpServletRequest request) throws IOException { // 매개변수로 request를 받는다
		// static : 생성없이 호출 가능한 메서드
		String requestJsonData = null;
		StringBuilder builder = new StringBuilder();
		String readLineData = null;
		BufferedReader reader = request.getReader();

		while ((readLineData = reader.readLine()) != null) {
			builder.append(readLineData);
		}
		requestJsonData = builder.toString(); // json데이터가 만들어진다

		return requestJsonData;
	}

	public static <T> T convertJsonData(HttpServletRequest request, Class<T> classofT) throws IOException {
		// public String void test() 와 같은 구조 / <T> 제네릭 , T 리턴값
		String requestJsonData = null;
		StringBuilder builder = new StringBuilder();
		String readLineData = null;
		BufferedReader reader = request.getReader();

		while ((readLineData = reader.readLine()) != null) {
			builder.append(readLineData);
		}
		requestJsonData = builder.toString();

		Gson gson = new Gson();

		return gson.fromJson(requestJsonData, classofT);

	}
}
