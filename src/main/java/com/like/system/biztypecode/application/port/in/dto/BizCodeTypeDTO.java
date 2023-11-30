package com.like.system.biztypecode.application.port.in.dto;

import static org.springframework.util.StringUtils.hasText;

import jakarta.validation.constraints.NotBlank;

import com.like.system.biztypecode.adapter.out.persistence.jpa.entity.JpaBizCodeType;
import com.like.system.biztypecode.adapter.out.persistence.jpa.entity.JpaBizTypeEnum;
import com.like.system.biztypecode.adapter.out.persistence.jpa.entity.QJpaBizCodeType;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

import lombok.AccessLevel;
import lombok.Builder;

public class BizCodeTypeDTO {

	public record Search(
			@NotBlank(message="조직 코드를 선택해주세요.")
			String organizationCode,
			String typeId,
			String typeName,			
			String bizType
			) {
		
		private static final QJpaBizCodeType qType = QJpaBizCodeType.jpaBizCodeType;
		
		public BooleanBuilder getBooleanBuilder() {
			BooleanBuilder builder = new BooleanBuilder();
			
			builder
				.and(eqOrganizationCode(this.organizationCode))
				.and(eqId(this.typeId))
				.and(likeName(this.typeName))				
				.and(eqBizType(this.bizType))
				;
			
			return builder;
		}
		
		private BooleanExpression eqOrganizationCode(String organizationCode) {
			return qType.id.organizationCode.eq(organizationCode);
		}
		
		private BooleanExpression eqId(String id) {
			return hasText(id) ? qType.id.typeId.eq(id) : null;				
		}
		
		private BooleanExpression likeName(String name) {
			return hasText(name) ? qType.name.like("%" + name + "%") : null;			
		}		
		
		private BooleanExpression eqBizType(String bizType) {
			return hasText(bizType) ? qType.bizType.eq(JpaBizTypeEnum.valueOf(bizType)) : null;
		}
	}	
	
	@Builder(access = AccessLevel.PRIVATE)
	public static record Form(
			String clientAppUrl,
			String organizationCode,
			String typeId,			
			String typeName,			
			Integer sequence,
			String bizType,
			String comment
			) {
				
		public static Form convert(JpaBizCodeType entity) {			
			if (entity == null) return null;
			
			return Form.builder()
					   .organizationCode(entity.getId().getOrganizationCode())
					   .typeId(entity.getId().getTypeId())
					   .typeName(entity.getName())					   
					   .sequence(entity.getSequence())
					   .bizType(entity.getBizType() == null ? null : entity.getBizType().toString())
					   .comment(entity.getComment())
					   .build();
						
		}

		public JpaBizCodeType newEntity() {						
			JpaBizCodeType entity = new JpaBizCodeType(organizationCode, typeId, typeName, JpaBizTypeEnum.valueOf(bizType), comment);
			entity.setAppUrl(clientAppUrl);
			
			return entity;
		}
		
		public JpaBizCodeType modify(JpaBizCodeType entity) {			
			entity.modify(typeName
						 ,sequence
						 ,null
						 ,comment);
			
			entity.setAppUrl(clientAppUrl);
			
			return entity;
		}
				
	}	
	
	
}
