package com.like.system.role.adapter.out.persistence.jpa.entity;

import java.io.Serializable;

import com.like.core.jpa.domain.AbstractAuditEntity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access=AccessLevel.PROTECTED)
@Entity
@Table(name = "comrole")
public class JpaRole extends AbstractAuditEntity implements Serializable {
	
	private static final long serialVersionUID = 2010711918583959763L;

	@EmbeddedId
	JpaRoleId id;
	
	@Column(name="description")
	String description;	
	
	public JpaRole(String companyCode, String roleCode, String description) {		
		this.id = new JpaRoleId(companyCode, roleCode);
		this.description = description;
	}	
	
	public void modifyEntity(String description) {
		this.description = description;
	}		
	
	public String getCompanyCode() {
		return this.id.getCompanyCode();
	}

	public String getRoleCode() {
		return this.id.getRoleCode();
	}
	
	public String getDescription() {
		return description;
	}
}
