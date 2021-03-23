/*
 * Copyright (c) 2021 Tander, All Rights Reserved.
 */

package model;

public class ProgramInsert {
	private final String titleProgram;
	private final int period;

	public ProgramInsert(String titleProgram, int period) {
		this.titleProgram = titleProgram;
		this.period=period;
	}


	public int getPeriod() {
		return period;
	}

	public String getTitleProgram() {
		return titleProgram;
	}

	@Override
	public String toString() {
		return titleProgram;
	}

}