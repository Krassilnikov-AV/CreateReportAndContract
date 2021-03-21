/*
 * Copyright (c) 2021 Tander, All Rights Reserved.
 */

package model;

import java.util.List;

/**
 * Класс ShedulesInsert
 */
public class ShedulesInsert {

	private final List<SheduleInsert> shedules;

	public ShedulesInsert(List<SheduleInsert> shedules) {
		this.shedules = shedules;
	}


	public List<SheduleInsert> getShedules() {
		return shedules;
	}
}