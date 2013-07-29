package org.vplus.core.persistence;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import org.vplus.core.exception.CrudException;
import org.vplus.core.generics.Model;

public enum Direction {

	ASC {
		@Override
		public boolean isAsc() {
			return true;
		}

		@Override
		public Order makeOrder(CriteriaBuilder b,
				Root<Model> root, String order) throws CrudException {
			return b.asc(getPath(root, order));
		}
	},
	DESC {
		@Override
		public boolean isAsc() {
			return false;
		}

		@Override
		public Order makeOrder(CriteriaBuilder b,
				Root<Model> root, String order) throws CrudException {
			return b.desc(getPath(root, order));
		}

	},
	NULL {
		@Override
		public boolean isAsc() {
			return true;
		}

		@Override
		public Order makeOrder(CriteriaBuilder b,
				Root<Model> root, String order) throws CrudException {
			validate(order);
			throw new CrudException("Direction is null");
		}
	};

	public abstract boolean isAsc();

	public abstract Order makeOrder(CriteriaBuilder b,
			Root<Model> root, String order) throws CrudException;
	
	protected void validate(String order) throws CrudException {
		if(order == null) {
			throw new CrudException("Order is null");
		}
	}
	
	protected Path<Object> getPath(Root<Model> root, String order) throws CrudException {
		validate(order);
		return root.get(order);
	}

}
