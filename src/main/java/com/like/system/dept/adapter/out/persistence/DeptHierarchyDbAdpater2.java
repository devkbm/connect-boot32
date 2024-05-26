package com.like.system.dept.adapter.out.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.system.dept.domain.DeptHierarchy;
import com.like.system.dept.domain.DeptHierarchyRepository;
import com.like.system.dept.domain.QDept;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class DeptHierarchyDbAdpater2 implements DeptHierarchyRepository {

	JPAQueryFactory queryFactory;
	private static final QDept qDept = QDept.dept;
	
	DeptHierarchyDbAdpater2(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;
	}
	
	@Override
	public List<DeptHierarchy> getAllNodes(String companyCode) {
		//return null;
		
		return queryFactory
				.select(Projections.bean(DeptHierarchy.class,
						qDept.id.companyCode,
						qDept.id.deptCode,
						qDept.deptNameKorean,
						qDept.deptAbbreviationKorean,
						qDept.deptNameEnglish,
						qDept.deptAbbreviationEnglish,
						qDept.period,
						qDept.seq,
						qDept.comment,
						qDept.parentDeptCode
						))
				.from(qDept)
				.where(qDept.id.companyCode.eq(companyCode))
				.orderBy(qDept.parentDeptCode.asc(), qDept.seq.asc())				
				.fetch();	
	}

}
