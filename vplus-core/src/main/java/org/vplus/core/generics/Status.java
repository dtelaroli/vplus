package org.vplus.core.generics;

public enum Status {
	
	INACTIVE {
		@Override
		public boolean isActive() {
			return false;
		}
		@Override
		public boolean isInactive() {
			return true;
		}
		@Override
		public boolean isRemoved() {
			return false;
		}
	}, 
	
	ACTIVE {
		@Override
		public boolean isActive() {
			return true;
		}
		@Override
		public boolean isInactive() {
			return false;
		}
		@Override
		public boolean isRemoved() {
			return false;
		}
	}, 
	
	REMOVED {
		@Override
		public boolean isActive() {
			return false;
		}
		@Override
		public boolean isInactive() {
			return false;
		}
		@Override
		public boolean isRemoved() {
			return true;
		}
	};
	
	public abstract boolean isActive();
	
	public abstract boolean isInactive();

	public abstract boolean isRemoved();
	
}