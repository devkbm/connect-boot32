package com.like.system.hierarchycode.application.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.system.hierarchycode.application.port.in.dto.CodeDTO;
import com.like.system.hierarchycode.application.port.in.dto.CodeHierarchy;
import com.like.system.hierarchycode.domain.HierarchyCodeSelectRepository;


@Transactional(readOnly=true)
@Service
public class HierarchyCodeSelectService {

	HierarchyCodeSelectRepository repository;
	
	HierarchyCodeSelectService(HierarchyCodeSelectRepository repository) {
		this.repository = repository;
	}
	
	public List<CodeHierarchy> getCodeHierarchyList(CodeDTO.Search searchCondition) {		
		
		return repository.getCodeHierarchyList(searchCondition);
	}
}
