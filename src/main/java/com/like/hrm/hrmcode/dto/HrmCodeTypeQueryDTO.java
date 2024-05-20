package com.like.hrm.hrmcode.dto;

import static org.springframework.util.StringUtils.hasText;

import java.io.Serializable;

import com.like.hrm.hrmcode.domain.QHrmCodeType;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

import lombok.Data;

@Data
public class HrmCodeTypeQueryDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private final QHrmCodeType qType = QHrmCodeType.hrmCodeType;
					
	private String typeId;
	
	private String typeName;
	
	private String appointmentType;
				
	public BooleanBuilder getBooleanBuilder() {
		BooleanBuilder builder = new BooleanBuilder();
		
		builder		
			.and(likeCodeName(this.typeName));
				
		return builder;
	}			
	
	private BooleanExpression likeCodeName(String typeName) {
		return hasText(typeName) ? qType.name.like("%" + typeName + "%") : null;					
	}
			
}