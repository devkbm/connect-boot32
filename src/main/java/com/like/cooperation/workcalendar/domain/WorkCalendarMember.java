package com.like.cooperation.workcalendar.domain;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.like.system.core.jpa.domain.AbstractAuditEntity;
import com.like.system.user.domain.SystemUser;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString(exclude = {"workCalendar"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "GRWWORKCALENDARUSER")
public class WorkCalendarMember extends AbstractAuditEntity {
	
	@EmbeddedId
	WorkCalendarMemberId id;
	
	@JsonBackReference
	@MapsId("workCalendar")
	@ManyToOne
	@JoinColumn(name="ID", referencedColumnName = "ID")
	WorkCalendar workCalendar;
	
	public WorkCalendarMember(WorkCalendar workCalendar, SystemUser user) {
		this.id = new WorkCalendarMemberId(workCalendar.getId(), user.getId().getUserId()) ;
		this.workCalendar = workCalendar;
	}
	
	public String getUserId() {
		return this.getId().getUserId();
	}
	
}
