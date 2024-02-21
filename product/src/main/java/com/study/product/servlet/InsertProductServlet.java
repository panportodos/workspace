package com.study.product.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.product.dto.InsertProductReqDto;
import com.study.product.service.ProductService;
import com.study.product.utils.RequestUtil;
import com.study.product.utils.ResponseEntity;

@WebServlet("/product")
public class InsertProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductService productService;

	public InsertProductServlet() {
		super();
		productService = ProductService.getInstance();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// json을 객체로 변환하는 작업
//		String requestJsonData = RequestUtil.getJsonData(request); // 앞으로 json형식을 받아올 때는 전부 이런 형식으로 처리를 하면 된다

		InsertProductReqDto reqDto = RequestUtil.convertJsonData(request, InsertProductReqDto.class); // gson을 통해 json을
																										// 해당 자료형의 객체로
																										// 변환
		// 이제 자료형만 바꿔주면 된다
		System.out.println(reqDto + "서블릿");
		if (productService.isDuplicatedProductName(reqDto.getProductName())) {
			Map<String, Object> responseMap = new HashMap<>();
			responseMap.put("errorMessage", "이미 등록된 상품명입니다.");

			ResponseEntity.ofJson(response, 400, responseMap);
			return;
		}

		ResponseEntity.ofJson(response, 201, productService.addProduct(reqDto));

//		Product product = gson.fromJson(builder.toString(), Product.class);
//		System.out.println(product);
//		System.out.println(product.getName() + "받음?");
//		System.out.println(product.getPrice());
//
//		ProductDao productDao = ProductDao.getInstance();
//		Product findProduct = productDao.findProductByName(product.getName());
//
//		if (findProduct != null) {
//			response.setStatus(400);
//			Map<String, Object> errorMap = new HashMap<>();
//			errorMap.put("errorMessage", "이미 등록된 이름입니다");
//			response.setContentType("application/json");
//			response.getWriter().println(gson.toJson(errorMap));
//			return;
//		}
//
//		int successCount = productDao.saveProduct(product);
//
//		Map<String, Object> responseMap = new HashMap<>();
//		responseMap.put("status", 201);
//		responseMap.put("data", "응답 데이터");
//		responseMap.put("successCount", successCount);
//
//		response.setStatus(201);
//		response.setContentType("application/json");
//
//		PrintWriter writer = response.getWriter();
//		writer.println(gson.toJson(responseMap));
//	
	}

}
