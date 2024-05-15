package com.like.system.holiday.domain;

import java.io.Serializable;

import com.like.core.jpa.domain.AbstractAuditEntity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Table(name = "COMHOLIDAY")
@Entity
public class Holiday extends AbstractAuditEntity implements Serializable {	
	
	private static final long serialVersionUID = -1170840034409404138L;
	
	@EmbeddedId
	private HolidayId id;
		
	@Column(name="HOLIDAY_NM")
	private String holidayName;
	
	@Column(name="CMT")
	private String comment;		
	
	public void modify(String holidayName, String comment) {
		this.holidayName = holidayName;
		this.comment = comment;
	}
}
