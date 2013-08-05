package org.vplus.core.model;

public enum Status {
	
	Inactive {
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
	
	Active {
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
	
	Removed {
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