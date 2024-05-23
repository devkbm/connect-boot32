package com.like.system.dept.adapter.out.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.system.dept.application.port.out.DeptHierarchySelectPort;
import com.like.system.dept.domain.QDept;
import com.like.system.dept.dto.DeptHierarchyNgZorro;
import com.like.system.dept.dto.DeptQueryDTO;
import com.like.system.dept.dto.QDeptHierarchyNgZorro;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class DeptHierarchyDbAdapter implements DeptHierarchySelectPort {
	
	JPAQueryFactory queryFactory;
	private static final QDept qDept = QDept.dept;
	
	public DeptHierarchyDbAdapter(JPAQueryFactory queryFactory) {
	    this.queryFactory = queryFactory;
	}

	@Override
	public List<DeptHierarchyNgZorro> select(DeptQueryDTO dto) {				  		
		return queryFactory
				.select(this.projection(qDept))				
				.from(qDept)
				.where(qDept.id.companyCode.eq(dto.companyCode()))
				.orderBy(qDept.parentDeptCode.asc(), qDept.seq.asc())				
				.fetch();
	}
	
	private QDeptHierarchyNgZorro projection(QDept qDept) {
		return new QDeptHierarchyNgZorro(qDept.parentDept.id.deptCode
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
