package com.like.system.webresource.application.port.in;

import com.like.system.webresource.application.port.dto.WebResourceSaveDTO;

public interface WebResourceSaveUseCase {
	void save(WebResourceSaveDTO dto);
}
