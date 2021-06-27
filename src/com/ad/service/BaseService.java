package com.ad.service;

import org.hibernate.SessionFactory;

public abstract class BaseService {
	protected SessionFactory sessionFactory;
	
	public BaseService(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
