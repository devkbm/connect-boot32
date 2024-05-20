package com.like.system.hierarchycode.application.dto;

import java.time.LocalDateTime;

import com.like.system.hierarchycode.domain.Code;

import lombok.Builder;

@Builder
public record HierarchySaveDTO(
		LocalDateTime createdDt,
		String createdBy,
		LocalDateTime modifiedDt,
		String modifiedBy,
		String clientAppUrl,			
		String companyCode,
		String systemTypeCode,
		String codeId,
		String parentId,			
		String code,
		String codeName,
		String codeNameAbbreviation,
		LocalDateTime fromDate,
		LocalDateTime toDate,
		Integer hierarchyLevel,
		Integer seq,			
		Integer lowLevelCodeLength,
		String cmt
		) {
	
	public Code newCode(Code parentCode) {
		
		Code entity = Code.builder()
						  .systemTypeCode(this.systemTypeCode)
						  .parentCode(parentCode)
						  .code(this.code)
						  .codeName(this.codeName)
						  .codeNameAbbreviation(this.codeNameAbbreviation)				
						  .fromDate(this.fromDate)
						  .toDate(this.toDate)
						  .seq(this.seq)							  
						  .lowLevelCodeLength(this.lowLevelCodeLength)
						  .cmt(this.cmt)
						  .build();
		
		entity.setAppUrl(clientAppUrl);
		
		return entity;
	}
	
	public void modifyCode(Code code) {
		code.modifyEntity(this.codeName
						 ,this.codeNameAbbreviation
						 ,this.fromDate
						 ,this.toDate
						 ,this.seq							 
						 ,this.lowLevelCodeLength
						 ,this.cmt);
		
		code.setAppUrl(clientAppUrl);
	}
	
	public static HierarchySaveDTO convertDTO(Code entity) {					
		
		Code parent = entity.getParentCode();
									
		return HierarchySaveDTO.builder()
				   .createdDt(entity.getCreatedDt())
				   .createdBy(entity.getCreatedBy() == null ? null : entity.getCreatedBy().getLoggedUser())
				   .modifiedDt(entity.getModifiedDt())
				   .modifiedBy(entity.getModifiedBy() == null ? null : entity.getModifiedBy().getLoggedUser())					   
				   .systemTypeCode(entity.getId().getSystemTypeCode())
				   .codeId(entity.getId().getCodeId())
				   .parentId(parent == null ? null : parent.getId().getCodeId())
				   .code(entity.getCode())
				   .codeName(entity.getCodeName())
				   .codeNameAbbreviation(entity.getCodeNameAbbreviation())								
				   .fromDate(entity.getFromDate())
				   .toDate(entity.getToDate())
				   .hierarchyLevel(entity.getHierarchyLevel())
				   .seq(entity.getSeq())					   
				   .lowLevelCodeLength(entity.getLowLevelCodeLength())
				   .cmt(entity.getCmt())
				   .build();	
	}
}