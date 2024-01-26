package com.like.hrm.staff.adapter.out.persistence;

import org.springframework.stereotype.Repository;

import com.like.hrm.staff.adapter.out.persistence.jpa.repository.StaffJpaRepository;
import com.like.hrm.staff.application.port.out.StaffCommandDbPort;
import com.like.hrm.staff.domain.model.Staff;
import com.like.hrm.staff.domain.model.StaffId;

@Repository
public class StaffCommandDbAdapter implements StaffCommandDbPort {

	StaffJpaRepository repository;
	
	StaffCommandDbAdapter(StaffJpaRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public Staff select(String companyCode, String staffNo) {
		return this.repository.findById(new StaffId(companyCode, staffNo)).orElse(null);
	}

	@Override
	public void save(Staff entity) {
		this.repository.save(entity);		
	}

	@Override
	public void delete(String companyCode, String staffNo) {
		this.repository.deleteById(new StaffId(companyCode, staffNo));	
	}

}
