/*
 * Copyright (c) 2021 Tander, All Rights Reserved.
 */

package model;

import java.util.List;


public class GroupsInsert {

	private final List<GroupInsert> groups;

	public GroupsInsert(List<GroupInsert> groups) {
		this.groups = groups;
	}

	public List<GroupInsert> getShedules() {
		return groups;
	}
}