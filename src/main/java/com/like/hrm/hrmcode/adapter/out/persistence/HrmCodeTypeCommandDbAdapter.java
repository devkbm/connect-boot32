package com.like.hrm.hrmcode.adapter.out.persistence;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.like.hrm.hrmcode.adapter.out.persistence.springdata.HrmCodeTypeRepository;
import com.like.hrm.hrmcode.application.port.out.HrmCodeTypeCommandDbPort;
import com.like.hrm.hrmcode.domain.HrmCodeType;

@Repository
public class HrmCodeTypeCommandDbAdapter implements HrmCodeTypeCommandDbPort {

	HrmCodeTypeRepository repository;
	
	HrmCodeTypeCommandDbAdapter(HrmCodeTypeRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public Optional<HrmCodeType> select(String id) {
		return this.repository.findById(id);
	}

	@Override
	public void save(HrmCodeType entity) {
		this.repository.save(entity);
	}

	@Override
	public void delete(String id) {
		this.repository.deleteById(id);
	}

}
