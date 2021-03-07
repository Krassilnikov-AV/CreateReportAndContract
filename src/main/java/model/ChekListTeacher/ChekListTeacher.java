/*
 * Copyright (c) 2021 Tander, All Rights Reserved.
 */

package model.ChekListTeacher;

/**
 * Класс ChekListTeacher
 */
public class ChekListTeacher {
	private final String dateStart;
	private final String timeStart;
	private final String tech;

	public ChekListTeacher(String dateStart, String timeStart, String tech) {
		this.dateStart = dateStart;
		this.timeStart = timeStart;
		this.tech = tech;
	}

	/**
	 * Возвращает
	 *
	 * @return
	 */
	public String getDateStart() {
		return dateStart;
	}

	/**
	 * Возвращает
	 *
	 * @return
	 */
	public String getTimeStart() {
		return timeStart;
	}

	/**
	 * Возвращает
	 *
	 * @return
	 */
	public String getTech() {
		return tech;
	}

	@Override
	public String toString() {
		return "ChekListTeacher{" +
			"dateStart='" + dateStart + '\'' +
			", timeStart='" + timeStart + '\'' +
			", tech='" + tech + '\'' +
			'}';
	}
}