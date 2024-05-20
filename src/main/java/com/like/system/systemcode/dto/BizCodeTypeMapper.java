package com.like.system.systemcode.dto;

import com.like.system.systemcode.domain.BizCodeType;
import com.like.system.systemcode.domain.BizTypeEnum;

public class BizCodeTypeMapper {

	public static BizCodeType toEntity(BizCodeTypeSaveDTO dto) {
		return new BizCodeType(dto.companyCode()
							  ,dto.typeId()
							  ,dto.typeName()
							  ,BizTypeEnum.valueOf(dto.bizType())
							  ,dto.comment());		
	}
	
	
}
