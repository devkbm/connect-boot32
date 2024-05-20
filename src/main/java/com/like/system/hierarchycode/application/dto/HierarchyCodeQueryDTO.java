package com.like.system.hierarchycode.application.dto;

import static org.springframework.util.StringUtils.hasText;

import com.like.system.hierarchycode.domain.QCode;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

public record HierarchyCodeQueryDTO(			
		String systemTypeCode,
		String parentId,
		String code,
		String codeName,
		String codeNameAbbreviation,
		Boolean isUse			
		) {
	
	private static final QCode qType = QCode.code1;
	
	public BooleanBuilder getCondition() {
		BooleanBuilder builder = new BooleanBuilder();
				
		builder				
			.and(eqSystemTypeCode(this.systemTypeCode))
			.and(eqParentCode(this.parentId))	 	// 특정 아이디의 하위 코드 검색
			.and(likeCode(this.code))
			.and(likeCodeName(this.codeName))
			.and(likeCodeNameAbbreviation(this.codeNameAbbreviation));
												
		/*
		if (this.isUse) {																						
			builder.and(qType.enabled());											
		} 
		*/
		
		return builder;
	}
				
	private BooleanExpression eqSystemTypeCode(String systemTypeCode) {
		return hasText(systemTypeCode) ? qType.id.systemTypeCode.eq(systemTypeCode) : null;					
	}
			
	private BooleanExpression eqParentCode(String parentId) {
		return hasText(parentId) ? qType.parentCode.id.codeId.eq(parentId) : null;					
	}		
	
	private BooleanExpression likeCode(String code) {
		return hasText(code) ? qType.code.like("%"+code+"%") : null;					
	}
	
	private BooleanExpression likeCodeName(String codeName) {
		return hasText(codeName) ? qType.codeName.like("%"+codeName+"%") : null;					
	}
	
	private BooleanExpression likeCodeNameAbbreviation(String codeNameAbbreviation) {
		return hasText(codeNameAbbreviation) ? qType.codeNameAbbreviation.like("%"+codeNameAbbreviation+"%") : null;
	}
}