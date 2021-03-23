/*
 * Copyright (c) 2021 Tander, All Rights Reserved.
 */

package model;

public class TeacherInsert {
	private final String nameTeacher;

	public TeacherInsert(String nameTeacher) {
		this.nameTeacher = nameTeacher;
	}

	public String getNameTeacher() {
		return nameTeacher;
	}

	@Override
	public String toString() {
		return nameTeacher;
	}
}