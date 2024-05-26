package com.like.system.dept.domain;

import java.util.List;

public interface DeptHierarchyRepository {

	List<DeptHierarchy> getAllNodes(String companyCode);
}
