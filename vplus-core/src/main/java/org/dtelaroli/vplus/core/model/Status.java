package org.dtelaroli.vplus.core.model;

public enum Status {
	
	Disabled {
		@Override
		public boolean isActive() {
			return false;
		}
		@Override
		public boolean isDisabled() {
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
		public boolean isDisabled() {
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
		public boolean isDisabled() {
			return false;
		}
		@Override
		public boolean isRemoved() {
			return true;
		}
	};
	
	public abstract boolean isActive();
	
	public abstract boolean isDisabled();

	public abstract boolean isRemoved();
	
}