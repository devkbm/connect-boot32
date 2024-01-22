package com.like.hrm.hrmcode.adapter.out.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.hrm.hrmcode.application.port.dto.HrmCodeQueryDTO;
import com.like.hrm.hrmcode.application.port.out.HrmCodeQueryDbPort;
import com.like.hrm.hrmcode.domain.HrmCode;
import com.like.hrm.hrmcode.domain.QHrmCode;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class HrmCodeQueryDbAdapter implements HrmCodeQueryDbPort {

	JPAQueryFactory	queryFactory;	
	private static final QHrmCode qHrmCode = QHrmCode.hrmCode;
	
	HrmCodeQueryDbAdapter(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;
	}
	
	@Override
	public List<HrmCode> select(HrmCodeQueryDTO dto) {
		return queryFactory
				.selectFrom(qHrmCode)
				.where(dto.getBooleanBuilder())
				.fetch();
	}

	@Override
	public List<HrmCode> selectList(List<String> typeIds) {
		return queryFactory
				.selectFrom(qHrmCode)
				.where(qHrmCode.id.typeId.in(typeIds))
				.fetch();
	}

}
