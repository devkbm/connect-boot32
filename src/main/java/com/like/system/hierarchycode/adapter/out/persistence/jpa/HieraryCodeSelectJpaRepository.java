package com.like.system.hierarchycode.adapter.out.persistence.jpa;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.like.system.hierarchycode.application.dto.CodeHierarchy;
import com.like.system.hierarchycode.application.dto.HierarchyCodeQueryDTO;
import com.like.system.hierarchycode.application.dto.QCodeHierarchy;
import com.like.system.hierarchycode.domain.HierarchyCodeSelectRepository;
import com.like.system.hierarchycode.domain.QCode;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Primary
@Repository
public class HieraryCodeSelectJpaRepository implements HierarchyCodeSelectRepository {

	JPAQueryFactory	queryFactory;
	final QCode qCode = QCode.code1;				
	
	HieraryCodeSelectJpaRepository(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;
	}
	
	@Override
	public List<CodeHierarchy> getCodeHierarchyList(HierarchyCodeQueryDTO dto) { 							
		BooleanBuilder builder = new BooleanBuilder();
		
		builder.and(qCode.id.systemTypeCode.eq(dto.systemTypeCode()))
			   .and(qCode.enabled());
		
		return queryFactory
				.select(this.projection(qCode))
				.from(qCode)
				.where(builder)			
				.orderBy(qCode.hierarchyLevel.asc(), qCode.seq.asc())
				.fetch();
	}	
	
	private QCodeHierarchy projection(QCode qCode) {		
		return new QCodeHierarchy(				
				qCode.id.codeId,
				qCode.id.systemTypeCode,				
				qCode.code, 
				qCode.codeName, 
				qCode.codeNameAbbreviation,
				qCode.parentCode.id.codeId,
				qCode.fromDate, 
				qCode.toDate, 
				qCode.seq, 
				qCode.cmt);		
	}

}
