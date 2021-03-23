/*
 * Copyright (c) 2021 Tander, All Rights Reserved.
 */

package model;

import java.util.List;


public class ShedulesSearch {

	private final List<SheduleSearch> shedules;

	public ShedulesSearch(List<SheduleSearch> shedules) {
		this.shedules = shedules;
	}

	public List<SheduleSearch> getShedules() {
		return shedules;
	}
}