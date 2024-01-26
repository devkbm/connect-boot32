package com.like.system.systemcode.application.port.dto;

import lombok.Builder;

@Builder
public record BizCodeTypeSaveDTO(
		String clientAppUrl,
		String companyCode,
		String typeId,			
		String typeName,			
		Integer sequence,
		String bizType,
		String comment
		) {

}
