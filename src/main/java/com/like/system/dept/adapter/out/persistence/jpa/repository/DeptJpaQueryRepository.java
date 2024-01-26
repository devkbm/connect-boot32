package com.like.system.dept.adapter.out.persistence.jpa.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.system.dept.application.port.dto.DeptHierarchyResponse;
import com.like.system.dept.application.port.dto.QDeptHierarchyResponse;
import com.like.system.dept.domain.QDept;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class DeptJpaQueryRepository {

	private JPAQueryFactory  queryFactory;
	private static final QDept qDept = QDept.dept;		
	
	public DeptJpaQueryRepository(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;		
	}
	
	
	public List<DeptHierarchyResponse> getDeptHierarchy(String companyCode) {
		List<DeptHierarchyResponse> rootNodeList = this.getDeptRootNodeList(companyCode);
		
		List<DeptHierarchyResponse> result = this.addDeptChildNodeList(companyCode, rootNodeList);
		
		return result;
	}
	
	private List<DeptHierarchyResponse> addDeptChildNodeList(String companyCode, List<DeptHierarchyResponse> list) {
		List<DeptHierarchyResponse> children = null;
		
		for ( DeptHierarchyResponse node : list) {
			
			children = getDeptChildNodeList(companyCode, node.getDeptCode());
			
			if (children.isEmpty()) {
				node.setLeaf(true);
				continue;
			} else {
				node.setChildren(children);
				node.setLeaf(false);
				
				// 재귀 호출
				this.addDeptChildNodeList(companyCode, children);
			}			
		}
		
		return list;
	}

	private List<DeptHierarchyResponse> getDeptRootNodeList(String companyCode) {
		return queryFactory
				.select(this.getDeptHierarchy(qDept))				
				.from(qDept)
				.where(qDept.id.companyCode.eq(companyCode), qDept.isRootNode())
				.orderBy(qDept.seq.asc())				
				.fetch();
	}
	
	private List<DeptHierarchyResponse> getDeptChildNodeList(String companyCode, String parentDeptCode) {
		return queryFactory
				.select(this.getDeptHierarchy(qDept))
				.from(qDept)
				.where(qDept.id.companyCode.eq(companyCode), qDept.parentDept.id.deptCode.eq(parentDeptCode))
				.orderBy(qDept.seq.asc())
				.fetch();
	}
	
	private QDeptHierarchyResponse getDeptHierarchy(QDept qDept) {
		return new QDeptHierarchyResponse(qDept.parentDept.id.deptCode
										 ,qDept.id.companyCode										 
										 ,qDept.id.deptCode
										 ,qDept.deptNameKorean
										 ,qDept.deptAbbreviationKorean
										 ,qDept.deptNameEnglish
										 ,qDept.deptAbbreviationEnglish
										 ,qDept.period
										 ,qDept.seq
										 ,qDept.comment);
	}

}
