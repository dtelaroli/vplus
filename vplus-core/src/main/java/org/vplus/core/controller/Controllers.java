package org.vplus.core.controller;

public abstract class Controllers {

	public static Class<ActionList> list() {
		return ActionList.class;
	}

	public static Class<ActionDelete> delete() {
		return ActionDelete.class;
	}

	public static Class<ActionLoad> load() {
		return ActionLoad.class;
	}

	public static Class<ActionSave> save() {
		return ActionSave.class;
	}

}
