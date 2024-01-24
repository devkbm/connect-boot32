package com.like.system.systemcode.application.port.dto;

import com.like.system.systemcode.adapter.out.persistence.jpa.entity.JpaBizCode;
import com.like.system.systemcode.adapter.out.persistence.jpa.entity.JpaBizCodeType;

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

	public static BizCodeSaveDTO convert(JpaBizCode entity) {			
		if (entity == null) return null;
		
		return BizCodeSaveDTO.builder()
							   .typeId(entity.getId().getBizCodeTypeId().getTypeId())
							   .code(entity.getId().getCode())
							   .codeName(entity.getCodeName())
							   .useYn(entity.getUseYn())
							   .sequence(entity.getSequence())
							   .comment(entity.getComment())
							   .build();			
	}
	
	public JpaBizCode newEntity(JpaBizCodeType bizType) {			
		
		JpaBizCode entity = new JpaBizCode(bizType, code, codeName, useYn, sequence, comment); 
		
		entity.setAppUrl(clientAppUrl);
		
		return entity;
	}
	
	public JpaBizCode modify(JpaBizCode entity) {
		
		entity.modify(codeName
					 ,useYn
					 ,sequence						 
					 ,comment);
		
		entity.setAppUrl(clientAppUrl);
		
		return entity;
	}	
}
