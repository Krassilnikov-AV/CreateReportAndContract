/*
 * Copyright (c) 2021 Tander, All Rights Reserved.
 */

package model;

/**
 * Класс Shedule
 */
public class Shedule {
	private String id;
	private final String group;
	private final String pro;
	private final String audit;
	private final String type;
	private final String tech;

	public Shedule(String pro, String code, String audit, String type, String tech) {
		this.pro = pro;
		this.group = code;
		this.audit = audit;
		this.type = type;
		this.tech = tech;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public String getPro() {
		return pro;
	}


	public String getGroup() {
		return group;
	}

	public String getAudit() {
		return audit;
	}


	public String getType() {
		return type;
	}


	public String getTech() {
		return tech;
	}

	@Override
	public String toString() {
		return " //код группы: " + group + " //программа: " + pro + " //аудитория: " + audit +
			" //тип занятия: " + type + " //преподаватель: " + tech;
	}
}