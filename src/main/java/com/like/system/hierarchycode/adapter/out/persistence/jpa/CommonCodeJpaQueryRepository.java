package com.like.system.hierarchycode.adapter.out.persistence.jpa;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.system.hierarchycode.domain.Code;
import com.like.system.hierarchycode.domain.CommonCodeQueryRepository;
import com.like.system.hierarchycode.domain.QCode;
import com.like.system.hierarchycode.dto.CodeComboDTO;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class CommonCodeJpaQueryRepository implements CommonCodeQueryRepository {

	private JPAQueryFactory	queryFactory;
	private final QCode qCode = QCode.code1;
			
	public CommonCodeJpaQueryRepository(JPAQueryFactory	queryFactory) {
		this.queryFactory = queryFactory;
	}
	
	@Override
	public List<Code> getCodeList(String parentCodeId) {
		return queryFactory
				.selectFrom(qCode)
				.where(qCode.parentCode.id.codeId.eq(parentCodeId))
				.fetch();
	}
	
	@Override
	public List<Code> getCodeList(Predicate predicate) {
		return queryFactory
				.selectFrom(qCode)
				.where(predicate)
				.fetch();
	}

	

	@Override
	public List<CodeComboDTO> getCodeListByComboBox(String codeGroup) {
		return null; /* queryFactory
		.select(Projections.constructor(CodeComboDTO.class, qCommonCode.id.code,qCommonCode.codeName,qCommonCode.codeNameAbbreviation))
		.from(qCommonCode)
		.where(qCommonCode.id.codeGroup.eq(codeGroup))
		.fetch();*/
	}
	

}
