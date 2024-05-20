package com.like.hrm.hrmcode.dto;

import static org.springframework.util.StringUtils.hasText;

import java.io.Serializable;

import com.like.hrm.hrmcode.domain.QHrmCode;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class HrmCodeQueryDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private final QHrmCode qType = QHrmCode.hrmCode;
			
	@NotEmpty
	private String typeId;
			
	private String code;
			
	private String codeName;				
				
	public BooleanBuilder getBooleanBuilder() {
		BooleanBuilder builder = new BooleanBuilder();
		
		builder
			.and(eqTypeId(this.typeId))
			.and(likeCodeName(this.codeName));
					
		return builder;
	}
	
	private BooleanExpression eqTypeId(String typeId) {						
		return qType.id.typeId.eq(typeId);
	}
	
	private BooleanExpression likeCodeName(String codeName) {
		return hasText(codeName) ? qType.codeName.like("%" + codeName + "%") : null;					
	}
			
}