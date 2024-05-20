package com.like.system.webresource.application.port.out;

import java.util.List;

import com.like.system.webresource.domain.WebResource;
import com.like.system.webresource.dto.WebResourceQueryDTO;

public interface WebResourceQueryDbPort {
	List<WebResource> getResourceList(WebResourceQueryDTO condition);
}
