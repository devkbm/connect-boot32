package com.like.system.systemcode.domain;

import org.springframework.util.StringUtils;

import lombok.Getter;

@Getter
public class BizCodeId {

	BizCodeTypeId bizCodeTypeId;
	
	String code;

	public BizCodeId(String companyCode, String typeId, String code) {		
		if (!StringUtils.hasText(code)) throw new IllegalArgumentException("code는 필수 입력 값입니다.");
		
		this.bizCodeTypeId = new BizCodeTypeId(companyCode, typeId);		
		this.code = code;
	}
	
}
