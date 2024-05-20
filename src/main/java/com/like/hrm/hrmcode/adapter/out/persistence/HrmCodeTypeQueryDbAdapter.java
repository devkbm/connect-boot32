package com.like.hrm.hrmcode.adapter.out.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.hrm.hrmcode.application.port.out.HrmCodeTypeQueryDbPort;
import com.like.hrm.hrmcode.domain.HrmCodeType;
import com.like.hrm.hrmcode.domain.QHrmCodeType;
import com.like.hrm.hrmcode.dto.HrmCodeTypeQueryDTO;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class HrmCodeTypeQueryDbAdapter implements HrmCodeTypeQueryDbPort {

	JPAQueryFactory	queryFactory;
	private static final QHrmCodeType qHrmCodeType = QHrmCodeType.hrmCodeType;
	
	HrmCodeTypeQueryDbAdapter(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;
	}
	
	@Override
	public List<HrmCodeType> select(HrmCodeTypeQueryDTO dto) {
		return queryFactory
				.selectFrom(qHrmCodeType)
				.where(dto.getBooleanBuilder())
				.fetch();
	}

}
