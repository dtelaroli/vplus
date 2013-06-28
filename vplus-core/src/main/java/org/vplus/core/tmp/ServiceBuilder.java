package org.vplus.core.tmp;

public class ServiceBuilder {

	private ServiceImpl service;
	
	private ServiceBuilder(ServiceImpl service) {
		this.service = service;
	}

	public static ServiceBuilder create(ServiceImpl service) {
		return new ServiceBuilder(service);
	}

	public ServiceImpl build() {
		return service;
	}
	
}
