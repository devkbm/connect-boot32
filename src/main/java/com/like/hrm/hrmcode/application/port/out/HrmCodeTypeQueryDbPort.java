package com.like.hrm.hrmcode.application.port.out;

import java.util.List;

import com.like.hrm.hrmcode.domain.HrmCodeType;
import com.like.hrm.hrmcode.dto.HrmCodeTypeQueryDTO;

public interface HrmCodeTypeQueryDbPort {
	List<HrmCodeType> select(HrmCodeTypeQueryDTO dto);
}
