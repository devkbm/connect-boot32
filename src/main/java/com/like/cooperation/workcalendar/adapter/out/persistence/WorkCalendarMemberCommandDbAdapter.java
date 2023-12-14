package com.like.cooperation.workcalendar.adapter.out.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.cooperation.workcalendar.adapter.out.persistence.jparepository.WorkCalendarMemberJpaRepository;
import com.like.cooperation.workcalendar.application.port.out.WorkCalendarMemberCommandDbPort;
import com.like.cooperation.workcalendar.domain.WorkCalendarMember;

@Repository
public class WorkCalendarMemberCommandDbAdapter implements WorkCalendarMemberCommandDbPort {

	WorkCalendarMemberJpaRepository repository;
	
	WorkCalendarMemberCommandDbAdapter(WorkCalendarMemberJpaRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public void save(List<WorkCalendarMember> members) {
		this.repository.saveAll(members);
	}

	@Override
	public void delete(List<WorkCalendarMember> members) {
		this.repository.deleteAll(members);		
	}

}
