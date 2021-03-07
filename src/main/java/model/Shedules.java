/*
 * Copyright (c) 2021 Tander, All Rights Reserved.
 */

package model;


import java.util.List;

public class Shedules {
	private final List<Shedule>  shedules;

	public Shedules(List<Shedule> shedules) {
		this.shedules = shedules;
	}

	/**
	 * Возвращает
	 *
	 * @return
	 */
	public List<Shedule> getShedules() {
		return shedules;
	}

}