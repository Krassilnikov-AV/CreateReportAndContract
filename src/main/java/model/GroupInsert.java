/*
 * Copyright (c) 2021 Tander, All Rights Reserved.
 */

package model;


public class GroupInsert {

	private final String number;

	public GroupInsert(String number) {
		this.number = number;

	}
	public String getNumber() {
		return number;
	}

	@Override
	public String toString() {
		return  number;
	}
}