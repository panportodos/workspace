package com.study.product.vo;

import com.study.product.dto.InsertProductRespDto;
import com.study.product.dto.SearchProductRespDto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductVo { // database 명을 따라간다 // dto - json을 따라간다
	private int productId;
	private String productName;
	private int productPrice;
	private String productSize;

	public InsertProductRespDto toInsertDto(int successCount) {

		return InsertProductRespDto.builder().productId(productId).productName(productName).productPrice(productPrice)
				.productSize(productSize).successCount(successCount).build();
	}

	public SearchProductRespDto toSearchDto() {
		return SearchProductRespDto.builder().productId(productId).productName(productName).productPrice(productPrice)
				.productSize(productSize).build();
	}
}
