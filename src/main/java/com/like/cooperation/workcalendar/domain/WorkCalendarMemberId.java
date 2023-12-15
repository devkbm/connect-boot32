package com.like.cooperation.workcalendar.domain;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Embeddable
public class WorkCalendarMemberId implements Serializable {
		
	private static final long serialVersionUID = -9015996959356053573L;

	@Column(name="ID")
	Long workCalendar;
			
	@Column(name="USER_ID")
	String userId;	
	
	public WorkCalendarMemberId(Long workCalendar, String userId) {
		this.workCalendar = workCalendar;
		this.userId = userId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(userId, workCalendar);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WorkCalendarMemberId other = (WorkCalendarMemberId) obj;
		return Objects.equals(userId, other.userId) && Objects.equals(workCalendar, other.workCalendar);
	}
		
}
