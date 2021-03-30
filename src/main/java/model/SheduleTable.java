/*
 * Copyright (c) 2021 Tander, All Rights Reserved.
 */

package model;


import java.sql.Date;

public class SheduleTable {

	private String id;

	private final String codegroup;
	private final String group;
	private final String pro;
	private final java.sql.Date dateStart;
	private final java.sql.Date  timeStart;
	private final java.sql.Date  dateFinish;
	private final java.sql.Date  timeFinish;
	private final String audit;
	private final String type;
	private final String tech;


	public SheduleTable(String codegroup, String group, String pro,
						java.sql.Date dateStart, java.sql.Date timeStart,
						java.sql.Date dateFinish, java.sql.Date timeFinish,
						String audit, String type, String tech) {
		this.codegroup = codegroup;
		this.group = group;
		this.pro = pro;
		this.dateStart = dateStart;
		this.timeStart = timeStart;
		this.dateFinish = dateFinish;
		this.timeFinish = timeFinish;
		this.audit = audit;
		this.type = type;
		this.tech = tech;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {	this.id = id;}

	public String getCodegroup() {	return codegroup;	}

	public String getGroup() {	return group;	}

	public String getPro() {	return pro;	}

	public Date getDateStart() {	return dateStart;	}

	public Date getTimeStart() {	return timeStart;	}


	public Date getDateFinish() {	return dateFinish;	}

	public Date getTimeFinish() {	return timeFinish;	}

	public String getAudit() {	return audit;	}

	public String getType() {	return type;	}

	public String getTech() {	return tech;}

	@Override
	public String toString() {
		return codegroup+group+ pro + dateStart + timeStart
			+ dateFinish + timeFinish+ audit + type+ tech;
	}
}