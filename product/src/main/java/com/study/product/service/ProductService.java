package com.study.product.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.study.product.dao.ProductDao;
import com.study.product.dto.InsertProductReqDto;
import com.study.product.dto.InsertProductRespDto;
import com.study.product.dto.SearchProductRespDto;
import com.study.product.vo.ProductVo;

public class ProductService {

	private static ProductService instance;
	private ProductDao productDao;

	private ProductService() {
		productDao = ProductDao.getInstance();
	}

	public static ProductService getInstance() {
		if (instance == null) {
			instance = new ProductService();
		}
		return instance;
	}

	public boolean isDuplicatedProductName(String productName) { // 중복확인
		boolean result = false;
		result = productDao.findProductByProductName(productName) != null;
		return result;
	}

	public InsertProductRespDto addProduct(InsertProductReqDto insertProductReqDto) {
		ProductVo productVo = insertProductReqDto.toVo(); // 200주소
		System.out.println(productVo + "서비스");
		int successCount = productDao.save(productVo);

		return productVo.toInsertDto(successCount);
	}

	public List<SearchProductRespDto> searchProducts() {

		List<SearchProductRespDto> searchProductRespDtos = new ArrayList();

		List<ProductVo> productVos = productDao.getProductList();

		for (ProductVo productVo : productVos) {
			searchProductRespDtos.add(productVo.toSearchDto());
		}

		return productDao.getProductList().stream().map(vo -> vo.toSearchDto()).collect(Collectors.toList());
	}
}
