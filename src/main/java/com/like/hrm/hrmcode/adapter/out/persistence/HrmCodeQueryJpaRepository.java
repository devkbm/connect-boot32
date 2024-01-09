package com.like.hrm.hrmcode.adapter.out.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.hrm.hrmcode.domain.HrmCodeType;
import com.like.hrm.hrmcode.domain.QHrmCode;
import com.like.hrm.hrmcode.domain.QHrmCodeType;
import com.like.hrm.hrmcode.application.port.dto.HrmCodeQueryDTO;
import com.like.hrm.hrmcode.application.port.dto.HrmCodeTypeQueryDTO;
import com.like.hrm.hrmcode.domain.HrmCode;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class HrmCodeQueryJpaRepository implements HrmCodeQueryRepository {

	private JPAQueryFactory	queryFactory;
	private static final QHrmCodeType qHrmCodeType = QHrmCodeType.hrmCodeType;
	private static final QHrmCode qHrmCode = QHrmCode.hrmCode;
	
	public HrmCodeQueryJpaRepository(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;		
	}
	
	@Override
	public List<HrmCodeType> getHrmCodeTypeList(HrmCodeTypeQueryDTO condition) {
		return queryFactory
				.selectFrom(qHrmCodeType)
				.where(condition.getBooleanBuilder())
				.fetch();
	}

	@Override
	public List<HrmCode> getHrmCodeList(HrmCodeQueryDTO condition) {
		return queryFactory
				.selectFrom(qHrmCode)
				.where(condition.getBooleanBuilder())
				.fetch();
	}

}
