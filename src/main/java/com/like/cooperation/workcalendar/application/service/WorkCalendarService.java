package com.like.cooperation.workcalendar.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.cooperation.workcalendar.adapter.out.persistence.jparepository.WorkCalendarJpaRepository;
import com.like.cooperation.workcalendar.adapter.out.persistence.jparepository.WorkCalendarMemberJpaRepository;
import com.like.cooperation.workcalendar.domain.WorkCalendar;
import com.like.cooperation.workcalendar.domain.WorkCalendarMember;
import com.like.cooperation.workcalendar.domain.WorkCalendarMemberId;

@Transactional
@Service
public class WorkCalendarService {

	private WorkCalendarJpaRepository repository;
	private WorkCalendarMemberJpaRepository workGroupMemberRepository;	
	
	public WorkCalendarService(WorkCalendarJpaRepository repository
						   	  ,WorkCalendarMemberJpaRepository workGroupMemberRepository) {		
		this.repository = repository;
		this.workGroupMemberRepository = workGroupMemberRepository;		
	}						
	
	/**
	 * 업무그룹를 조회한다.
	 * @param id
	 * @return
	 */
	public WorkCalendar getWorkGroup(Long id) {
		return repository.findById(id).orElse(null);
	}				
	
	public void deleteWorkGroup(Long id) {
		repository.deleteById(id);
	}
	
	public WorkCalendarMember getWorkGroupMember(WorkCalendarMemberId id) {
		return workGroupMemberRepository.findById(id).orElse(null);
	}

	public void deleteWorkGroupMember(WorkCalendarMember workGroupMember) {
		workGroupMemberRepository.delete(workGroupMember);
	}
		

	
}
