package com.like.hrm.hrmcode.application.port.out;

import java.util.List;

import com.like.hrm.hrmcode.domain.HrmCode;
import com.like.hrm.hrmcode.dto.HrmCodeQueryDTO;

public interface HrmCodeQueryDbPort {
	List<HrmCode> select(HrmCodeQueryDTO dto);
	
	List<HrmCode> selectList(List<String> typeIds);
}
