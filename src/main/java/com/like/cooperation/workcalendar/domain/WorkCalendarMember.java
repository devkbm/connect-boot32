package com.like.cooperation.workcalendar.domain;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.like.core.jpa.domain.AbstractAuditEntity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString(exclude = {"workCalendar"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
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
	
	public WorkCalendarMember(WorkCalendar workCalendar, String user) {
		this.id = new WorkCalendarMemberId(workCalendar.getId(), user) ;
		this.workCalendar = workCalendar;
	}
	
	public String getUserId() {
		return this.getId().getUserId();
	}
	
}
