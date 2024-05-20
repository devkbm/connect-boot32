package com.like.system.dept.dto;

import static org.springframework.util.StringUtils.hasText;

import com.like.system.dept.domain.QDept;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

import jakarta.validation.constraints.NotEmpty;

public record DeptQueryDTO(
		@NotEmpty(message="조직코드는 필수 입력 사항입니다.")
		String companyCode,
		String deptCode,
		String deptName,
		Boolean isEnabled
		) {
	private static final QDept qType = QDept.dept;
	
	public BooleanBuilder getCondition() {
		BooleanBuilder builder = new BooleanBuilder();
		
		builder
			.and(eqOrganizationCode(this.companyCode))
			.and(likeDeptCode(this.deptCode))
			.and(likeDeptName(this.deptName));
													
		return builder;
	}
	
	private BooleanExpression eqOrganizationCode(String companyCode) {
		return hasText(companyCode) ? qType.id.companyCode.eq(companyCode) : null;										
	}
			
	private BooleanExpression likeDeptCode(String deptCode) {
		return hasText(deptCode) ? qType.id.deptCode.like("%"+deptCode+"%") : null;					
	}
	
	private BooleanExpression likeDeptName(String deptName) {			
		return hasText(deptName) ? qType.deptNameKorean.like("%"+deptName+"%") : null;			
	}
}
