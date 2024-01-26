package com.like.system.systemcode.domain;

import org.springframework.util.StringUtils;

import lombok.Getter;

@Getter
public class BizCodeTypeId {

	String companyCode;
	
	String typeId;
	
	public BizCodeTypeId(String companyCode, String typeId) {
		if (!StringUtils.hasText(companyCode)) throw new IllegalArgumentException("companyCode는 필수 입력 값입니다.");
		if (!StringUtils.hasText(typeId)) throw new IllegalArgumentException("typeId는 필수 입력 값입니다.");
		
		this.companyCode = companyCode;
		this.typeId = typeId;
	}
}
