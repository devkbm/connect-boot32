package com.like.system.user.domain;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(of = {"companyCode","userId"})
@Embeddable
public class SystemUserId implements Serializable {

	private static final long serialVersionUID = -8544637739358675046L;

	@Column(name="ORG_CD")
	String companyCode;
	
	@Column(name="USER_ID")
	String userId;
	
	protected SystemUserId() {}
	
	public SystemUserId(String companyCode, String userId) {
		this.companyCode = companyCode;
		this.userId = userId;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public String getUserId() {
		return userId;
	}
		
}
