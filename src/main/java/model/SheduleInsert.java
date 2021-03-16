/*
 * Copyright (c) 2021 Tander, All Rights Reserved.
 */

package model;

/**
 * Класс SheduleInsert
 */
public class SheduleInsert {
	private final String group;
	private final String pro;
	private final java.sql.Date dateStart;
	private final java.sql.Date  timeStart;
	private final java.sql.Date  dateFinish;
	private final java.sql.Date  timeFinish;
	private final String audit;
	private final String type;
	private final String tech;
	private final int period;

	public SheduleInsert(String group, String pro
		, java.sql.Date dateStart, java.sql.Date timeStart
		, java.sql.Date dateFinish, java.sql.Date timeFinish
		, String audit, String type
		, String tech, int period) {
		this.group = group;
		this.pro = pro;
		this.dateStart = dateStart;
		this.timeStart = timeStart;
		this.dateFinish = dateFinish;
		this.timeFinish = timeFinish;
		this.audit = audit;
		this.type = type;
		this.tech = tech;
		this.period = period;
	}

	/**
	 * Возвращает
	 *
	 * @return
	 */
	public String getGroup() {
		return group;
	}

	/**
	 * Возвращает
	 *
	 * @return
	 */
	public String getPro() {
		return pro;
	}

	/**
	 * Возвращает
	 *
	 * @return
	 */
	public java.sql.Date getDateStart() {
		return dateStart;
	}

	/**
	 * Возвращает
	 *
	 * @return
	 */
	public java.sql.Date getTimeStart() {
		return timeStart;
	}

	/**
	 * Возвращает
	 *
	 * @return
	 */
	public java.sql.Date getDateFinish() {
		return dateFinish;
	}

	/**
	 * Возвращает
	 *
	 * @return
	 */
	public java.sql.Date getTimeFinish() {
		return timeFinish;
	}

	/**
	 * Возвращает
	 *
	 * @return
	 */
	public String getAudit() {
		return audit;
	}

	/**
	 * Возвращает
	 *
	 * @return
	 */
	public String getType() {
		return type;
	}

	/**
	 * Возвращает
	 *
	 * @return
	 */
	public String getTech() {
		return tech;
	}

	/**
	 * Возвращает
	 *
	 * @return
	 */
	public int getPeriod() {
		return period;
	}

	@Override
	public String toString() {
		return "SheduleInsert{" +
			"group='" + group + '\'' +
			", pro='" + pro + '\'' +
			", dateStart='" + dateStart + '\'' +
			", timeStart='" + timeStart + '\'' +
			", dateFinish='" + dateFinish + '\'' +
			", timeFinish='" + timeFinish + '\'' +
			", audit='" + audit + '\'' +
			", type='" + type + '\'' +
			", tech='" + tech + '\'' +
			", period=" + period +
			'}';
	}
}