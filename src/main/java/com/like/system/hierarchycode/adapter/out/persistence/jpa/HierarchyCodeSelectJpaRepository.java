package com.like.system.hierarchycode.adapter.out.persistence.jpa;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.like.system.hierarchycode.application.port.in.dto.CodeHierarchy;
import com.like.system.hierarchycode.application.port.in.dto.CodeDTO.Search;
import com.like.system.hierarchycode.domain.HierarchyCodeSelectRepository;
import com.like.system.hierarchycode.domain.QCode;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;


@Repository
public class HierarchyCodeSelectJpaRepository implements HierarchyCodeSelectRepository {

	JPAQueryFactory	queryFactory;
	final QCode qCode = QCode.code1;
	
	HierarchyCodeSelectJpaRepository(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;
	}
		
	@Override
	public List<CodeHierarchy> getCodeHierarchyList(Search dto) {
		List<CodeHierarchy> rootNodeList = this.getCodeRootNodeList(dto.systemTypeCode());
		
		List<CodeHierarchy> result = this.addCodeChildrenList(rootNodeList);
		
		return result;
	}
	
	private List<CodeHierarchy> getCodeRootNodeList(String systemTypeCode) {
		
		BooleanBuilder builder = new BooleanBuilder();
		
		builder.and(qCode.isRootNode())
		       .and(qCode.enabled())
		       .and(qCode.id.systemTypeCode.eq(systemTypeCode));				
				
		return queryFactory
				.select(this.getCodehierarchyConstructor())
				.from(qCode)
				.where(builder)			
				.orderBy(qCode.seq.asc())
				.fetch();
	}
		
	private List<CodeHierarchy> getCodeChildNodeList(String parentCodeId) {
		BooleanBuilder builder = new BooleanBuilder();
		
		builder
			.and(qCode.parentCode.id.codeId.eq(parentCodeId))
			.and(qCode.enabled());
			
		return queryFactory
				.select(this.getCodehierarchyConstructor())
				.from(qCode)
				.where(builder)
				.orderBy(qCode.seq.asc())
				.fetch();
	}
	
	/**
	 * 하위 노드를 검색후 하위 노드가 존재하면 상위 노드에 하위 노드 추가함(재귀 함수)
	 * @param list
	 * @return
	 */
	private List<CodeHierarchy> addCodeChildrenList(List<CodeHierarchy> list) {
		List<CodeHierarchy> children = null;
		
		for ( CodeHierarchy code : list ) {
			
			children = getCodeChildNodeList(code.getId());
			
			if (children.isEmpty()) {
				code.setLeaf(true);
				continue;
			} else {
				code.setChildren(children);
				code.setLeaf(false);
				
				// 재귀 호출
				addCodeChildrenList(children);
			}				
		}
		
		return list;
	}
	
	/*
	 * 	public CodeHierarchy(String codeId, String systemTypeCode, String code, String codeName, String codeNameAbbreviation, String parentId, 
						 LocalDateTime fromDate, LocalDateTime toDate, int seq, String cmt)
	 */
	
	private ConstructorExpression<CodeHierarchy> getCodehierarchyConstructor() {		
		return Projections.constructor(
				CodeHierarchy.class,	
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
