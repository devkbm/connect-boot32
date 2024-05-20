package com.like.hrm.workchangeapp.adapter.out.persistence.jparepository;

import java.time.LocalDate;
import java.util.List;

import com.like.hrm.workchangeapp.domain.WorkChangeApplication;
import com.like.hrm.workchangeapp.domain.WorkChangeCode;
import com.like.hrm.workchangeapp.dto.DutyApplicationDTO;

public interface DutyApplicationQueryRepository {

	long getDutyApplicationCount(String staffId, List<WorkChangeCode> dutyCodeList, LocalDate fromDate, LocalDate toDate);
	
	List<WorkChangeApplication> getDutyApplicationList(DutyApplicationDTO.Search condition);
}
