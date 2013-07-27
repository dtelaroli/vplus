package org.vplus.core.controller;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;

import org.vplus.core.exception.CrudException;
import org.vplus.core.generics.Model;

public enum Order {

	ASC {
		@Override
		public boolean isAsc() {
			return true;
		}

		@Override
		public javax.persistence.criteria.Order makeOrder(CriteriaBuilder b,
				Root<Model> root, String order) throws CrudException {
			super.makeOrder(b, root, order);
			return b.asc(root.get(order));
		}
	},
	DESC {
		@Override
		public boolean isAsc() {
			return false;
		}

		@Override
		public javax.persistence.criteria.Order makeOrder(CriteriaBuilder b,
				Root<Model> root, String order) throws CrudException {
			super.makeOrder(b, root, order);
			return b.desc(root.get(order));
		}
	},
	NULL {
		@Override
		public boolean isAsc() {
			return true;
		}

		@Override
		public javax.persistence.criteria.Order makeOrder(CriteriaBuilder b,
				Root<Model> root, String order) throws CrudException {
			super.makeOrder(b, root, order);
			throw new CrudException("Direction is null");
		}
	};

	public abstract boolean isAsc();

	public javax.persistence.criteria.Order makeOrder(CriteriaBuilder b,
			Root<Model> root, String order) throws CrudException {
		if(order == null) {
			throw new CrudException("Order is null");
		}
		return null;
	}

}
