package com.like.system.hierarchycode.domain;

import java.util.List;

import com.like.system.hierarchycode.application.port.in.dto.CodeDTO;
import com.like.system.hierarchycode.application.port.in.dto.CodeHierarchy;

public interface HierarchyCodeSelectRepository {

	List<CodeHierarchy> getCodeHierarchyList(CodeDTO.Search dto);
}
