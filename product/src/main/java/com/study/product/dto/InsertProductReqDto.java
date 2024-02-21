package com.study.product.dto;

import com.study.product.vo.ProductVo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InsertProductReqDto {
	private String productName;
	private int productPrice;
	private String productSize;

	public ProductVo toVo() { // to메서드 : Dto에 들어있는 정보들을 Vo로 새로 생성해서 전달,
								// 리턴타입이 ProductVo인 메서드 toVo, 변환작업을 해준다

		return ProductVo.builder().productName(productName).productPrice(productPrice).productSize(productSize).build();

	}
}
