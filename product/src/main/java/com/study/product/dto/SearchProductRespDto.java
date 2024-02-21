package com.study.product.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SearchProductRespDto {

	private int productId;
	private String productName;
	private int productPrice;
	private String productSize;

}
