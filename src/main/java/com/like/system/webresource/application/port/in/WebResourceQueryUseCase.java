package com.like.system.webresource.application.port.in;

import java.util.List;

import com.like.system.webresource.dto.WebResourceQueryDTO;
import com.like.system.webresource.dto.WebResourceSaveDTO;

public interface WebResourceQueryUseCase {

	List<WebResourceSaveDTO> getResourceList(WebResourceQueryDTO condition);
}
