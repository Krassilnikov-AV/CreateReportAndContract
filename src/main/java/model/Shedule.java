/*
 * Copyright (c) 2021 Tander, All Rights Reserved.
 */

package model;

/**
 * Класс Shedule
 */
public class Shedule {

	private final String pro;
	private final String code;
	private final String audit;
	private final String type;
	private final String tech;

	public Shedule(String pro, String code, String audit, String type, String tech) {
		this.pro = pro;
		this.code = code;
		this.audit = audit;
		this.type = type;
		this.tech = tech;
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
	public String getCode() {
		return code;
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

	@Override
	public String toString() {
		return "Shedule{" +
			"pro='" + pro + '\'' +
			", code='" + code + '\'' +
			", audit='" + audit + '\'' +
			", type='" + type + '\'' +
			", tech='" + tech + '\'' +
			'}';
	}
}