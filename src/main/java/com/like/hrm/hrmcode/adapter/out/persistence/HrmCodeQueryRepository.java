package com.like.hrm.hrmcode.adapter.out.persistence;

import java.util.List;

import com.like.hrm.hrmcode.application.port.dto.HrmCodeQueryDTO;
import com.like.hrm.hrmcode.application.port.dto.HrmCodeTypeQueryDTO;
import com.like.hrm.hrmcode.domain.HrmCode;
import com.like.hrm.hrmcode.domain.HrmCodeType;

public interface HrmCodeQueryRepository {

	/**
	 * 인사 유형을 조회한다.
	 * @return
	 */
	List<HrmCodeType> getHrmCodeTypeList(HrmCodeTypeQueryDTO condition);
	
	/**
	 * 인사 유형 상세 코드 명단을 조회한다.
	 * @param condition
	 * @return 
	 */
	List<HrmCode> getHrmCodeList(HrmCodeQueryDTO condition);
	
}
