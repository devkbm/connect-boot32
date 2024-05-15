package com.like.cooperation.workcalendar.domain;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.like.core.jpa.domain.AbstractAuditEntity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Getter
@Entity
@Table(name = "GRWWORKCALENDAR")
@EntityListeners(AuditingEntityListener.class)
public class WorkCalendar extends AbstractAuditEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	Long id;
	
	@Column(name="CALENDAR_NAME")
	String name;
	
	@Column(name="COLOR")
	String color;
	
	@OneToMany(mappedBy = "workCalendar")
	List<WorkCalendarEvent> eventList;
	
	@OrderBy("USER_ID asc")
	@OneToMany(mappedBy = "workCalendar")
	Set<WorkCalendarMember> memberList;
	
	public WorkCalendar(String name, String color) {		
		this.name = name;
		this.color = color;
		this.eventList = new ArrayList<>();
		this.memberList = new LinkedHashSet<>();
	}
	
	public void modifyEntity(String name, String color) {
		this.name = name;
		this.color = color;
	}
			
}
