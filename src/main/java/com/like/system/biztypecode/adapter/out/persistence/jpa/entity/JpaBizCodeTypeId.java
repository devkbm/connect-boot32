package com.like.system.biztypecode.adapter.out.persistence.jpa.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Embeddable
public class JpaBizCodeTypeId implements Serializable {
	
	private static final long serialVersionUID = 6644923358649112843L;

	@Column(name="ORG_CD")
	String organizationCode;
		
	@Column(name="TYPE_ID")
	String typeId;
}
