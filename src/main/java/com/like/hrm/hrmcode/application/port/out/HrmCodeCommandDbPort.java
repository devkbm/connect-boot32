package com.like.hrm.hrmcode.application.port.out;

import java.util.Optional;

import com.like.hrm.hrmcode.domain.HrmCode;
import com.like.hrm.hrmcode.domain.HrmCodeId;

public interface HrmCodeCommandDbPort {
	Optional<HrmCode> select(HrmCodeId id);
	
	void save(HrmCode entity);
	
	void delete(HrmCodeId id);
}
