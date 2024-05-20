package com.like.hrm.hrmcode.dto;

import com.like.hrm.hrmcode.domain.HrmCodeType;

import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record HrmCodeTypeSaveDTO(
		String companyCode,
		String clientAppUrl,
		String typeId,
		String typeName,			
		Integer sequence,
		String comment
		) {
	
	public HrmCodeType newEntity() {
		return new HrmCodeType(this.typeId 
				   		  ,this.typeName
				   		  ,this.sequence					   		  
				   		  ,this.comment);
	}
	
	public HrmCodeType modify(HrmCodeType entity) {
		entity.modify(this.typeName						 
					 ,this.sequence						 
					 ,this.comment);
		return entity;
	}
	
	public static HrmCodeTypeSaveDTO toDTO(HrmCodeType entity) {
				
		return HrmCodeTypeSaveDTO.builder()
				   .typeId(entity.getId())
				   .typeName(entity.getName())					   
				   .sequence(entity.getSequence())
				   .comment(entity.getComment())
				   .build();						
		
	}
}