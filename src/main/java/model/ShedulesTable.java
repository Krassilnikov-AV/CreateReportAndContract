/*
 * Copyright (c) 2021 Tander, All Rights Reserved.
 */

package model;

import java.util.List;

/**
 * Класс ShedulesTable
 */
public class ShedulesTable {

	private final List<SheduleTable> shedulestable;

	public ShedulesTable(List<SheduleTable> shedulestable) {

		this.shedulestable = shedulestable;
	}

	public List<SheduleTable> getShedules() {
		return shedulestable;
	}
}