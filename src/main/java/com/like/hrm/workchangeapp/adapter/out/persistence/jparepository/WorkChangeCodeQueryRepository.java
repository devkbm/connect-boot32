package com.like.hrm.workchangeapp.adapter.out.persistence.jparepository;

import java.util.List;

import com.like.hrm.workchangeapp.domain.WorkChangeCode;
import com.like.hrm.workchangeapp.dto.WorkChangeCodeDTO;

public interface WorkChangeCodeQueryRepository {
	List<WorkChangeCode> getDutyCodeList(WorkChangeCodeDTO.SearchDutyCode condition);
}
