package com.like.system.systemcode.application.port.dto;

import com.like.system.systemcode.domain.BizCode;
import com.like.system.systemcode.domain.BizCodeType;

public class BizCodeMapper {

	public static BizCode toEntity(BizCodeSaveDTO dto, BizCodeType bizCodeType) {		
		return BizCode.builder()
					  .bizCodeType(bizCodeType)
					  .code(dto.code())
					  .useYn(dto.useYn())
					  .name(dto.codeName())
					  .sequence(dto.sequence())
					  .code(dto.comment())
					  .build();
	}
}
