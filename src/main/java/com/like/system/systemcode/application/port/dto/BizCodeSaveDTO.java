package com.like.system.systemcode.application.port.dto;

import lombok.Builder;

@Builder
public record BizCodeSaveDTO(
		String clientAppUrl,
		String organizationCode,
		String typeId,
		String code,
		String codeName,
		Boolean useYn,
		Integer sequence,
		String comment
		) {

}
