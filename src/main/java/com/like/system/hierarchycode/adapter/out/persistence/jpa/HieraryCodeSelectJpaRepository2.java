package com.like.system.hierarchycode.adapter.out.persistence.jpa;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.like.system.hierarchycode.application.port.in.dto.CodeDTO.Search;
import com.like.system.hierarchycode.application.port.in.dto.CodeHierarchy;
import com.like.system.hierarchycode.application.port.in.dto.QCodeHierarchy;
import com.like.system.hierarchycode.domain.HierarchyCodeSelectRepository;
import com.like.system.hierarchycode.domain.QCode;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Primary
@Repository
public class HieraryCodeSelectJpaRepository2 implements HierarchyCodeSelectRepository {

	JPAQueryFactory	queryFactory;
	final QCode qCode = QCode.code1;			
	
	List<CodeHierarchy> allList;
	
	HieraryCodeSelectJpaRepository2(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;
	}
	
	@Override
	public List<CodeHierarchy> getCodeHierarchyList(Search dto) {
		allList = this.getAllCodeList(dto.systemTypeCode());
		
		List<CodeHierarchy> rootList = allList.stream().filter(e -> !StringUtils.hasText(e.getParentId())).toList(); 					
		
		return addChildren(rootList);
	}
	
	// 재귀 호출 수정해야 함
	private List<CodeHierarchy> addChildren(List<CodeHierarchy> nodeList) {
				
		List<CodeHierarchy> children = null;
		
		for (CodeHierarchy node : nodeList) {		
			
			children = getChildren(node.getId());
			
			if (children.isEmpty()) {
				node.setLeaf(true);
				continue;
			} else { 					
				node.setLeaf(false);
				node.setChildren(children);
				
				addChildren(children);
			} 				
		}
		
		return nodeList;		
	}
	
	private List<CodeHierarchy> getAllCodeList(String systemTypeCode) {
		
		BooleanBuilder builder = new BooleanBuilder();
		
		builder.and(qCode.id.systemTypeCode.eq(systemTypeCode))
			   .and(qCode.enabled());
		
		return queryFactory
				.select(this.projection(qCode))
				.from(qCode)
				.where(builder)			
				.orderBy(qCode.hierarchyLevel.asc(), qCode.seq.asc())
				.fetch();
	}
	
	private List<CodeHierarchy> getChildren(String parentId) {
		return allList.stream().filter(e -> parentId.equals(e.getParentId())).toList();
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
