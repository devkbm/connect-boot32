package com.like.hrm.hrmcode.application.port.dto;

import com.like.hrm.hrmcode.domain.HrmCode;
import com.like.hrm.hrmcode.domain.HrmCodeId;

import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record HrmCodeSaveDTO(
		String organizationCode,
		String clientAppUrl,
		String typeId,
		String code,
		String codeName,
		boolean useYn,
		Integer sequence,
		String comment
		) {
	
	public HrmCode newEntity() {
		return new HrmCode(new HrmCodeId(typeId, code)										
							  		,this.codeName
							  		,this.useYn
							   		,this.sequence
							   		,this.comment);
	}
		
	public HrmCode modify(HrmCode entity) {
		entity.modify(this.codeName
					 ,this.useYn
					 ,this.sequence
					 ,this.comment);
		return entity;
	}

	public static HrmCodeSaveDTO convert(HrmCode entity) {
		if (entity == null) return null;
		
		return HrmCodeSaveDTO.builder()
				   .typeId(entity.getId().getTypeId())
				   .code(entity.getId().getCode())
				   .codeName(entity.getCodeName())
				   .useYn(entity.isUseYn())
				   .sequence(entity.getSequence())
				   .comment(entity.getComment())
				   .build();			
	}
}