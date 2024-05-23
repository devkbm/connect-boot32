package com.like.system.dept.domain;

import java.util.List;

public interface DeptHierarchySelectRepository {

	List<DeptHierarchy> getAllNodes(String companyCode);
}
