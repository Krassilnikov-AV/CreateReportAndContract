/*
 * Copyright (c) 2021 Tander, All Rights Reserved.
 */

package model;

import java.util.*;

public class TeachersInsert {

	private final Set<TeacherInsert> teachers;

	public TeachersInsert(Set<TeacherInsert> teachers) {
		this.teachers=teachers;
	}
	public Set<TeacherInsert> getTeachers() {
		return teachers;
	}
}