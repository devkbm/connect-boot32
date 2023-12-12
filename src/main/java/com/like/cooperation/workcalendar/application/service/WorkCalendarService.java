package com.like.cooperation.workcalendar.application.service;

import java.util.List;

import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.like.cooperation.workcalendar.adapter.out.persistence.jparepository.WorkCalendarJpaRepository;
import com.like.cooperation.workcalendar.adapter.out.persistence.jparepository.WorkCalendarMemberJpaRepository;
import com.like.cooperation.workcalendar.application.port.dto.WorkCalendarSaveDTO;
import com.like.cooperation.workcalendar.domain.WorkCalendar;
import com.like.cooperation.workcalendar.domain.WorkCalendarMember;
import com.like.cooperation.workcalendar.domain.WorkCalendarMemberId;
import com.like.system.user.adapter.out.persistence.jpa.repository.SystemUserRepository;
import com.like.system.user.domain.SystemUser;
import com.like.system.user.domain.SystemUserId;

@Service
@Transactional
public class WorkCalendarService {

	private WorkCalendarJpaRepository repository;
	private WorkCalendarMemberJpaRepository workGroupMemberRepository;
	private SystemUserRepository userRepository;
	
	public WorkCalendarService(WorkCalendarJpaRepository repository
						   ,WorkCalendarMemberJpaRepository workGroupMemberRepository	
						   ,SystemUserRepository userRepository) {		
		this.repository = repository;
		this.workGroupMemberRepository = workGroupMemberRepository;
		this.userRepository = userRepository;
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
