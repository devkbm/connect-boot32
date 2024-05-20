package com.like.system.hierarchycode.domain;

import java.util.List;

import com.like.system.hierarchycode.dto.CodeHierarchy;
import com.like.system.hierarchycode.dto.HierarchyCodeQueryDTO;

public interface HierarchyCodeSelectRepository {

	List<CodeHierarchy> getCodeHierarchyList(HierarchyCodeQueryDTO dto);
}
