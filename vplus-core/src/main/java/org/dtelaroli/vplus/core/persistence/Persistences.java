package org.dtelaroli.vplus.core.persistence;

public abstract class Persistences {

	public static Class<DBList> list() {
		return DBList.class;
	}

	public static Class<DBLoad> load() {
		return DBLoad.class;
	}

	public static Class<DBSave> save() {
		return DBSave.class;
	}

	public static Class<DBDelete> delete() {
		return DBDelete.class;
	}
	
}