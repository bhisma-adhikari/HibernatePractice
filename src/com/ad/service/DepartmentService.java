package com.ad.service;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.ad.entity.BaseEntity;
import com.ad.entity.Department;

public class DepartmentService extends BaseService {
	
	public DepartmentService(SessionFactory sessionFactory) {
		super(sessionFactory);
	}


	public Integer insert(Department department) {
		Session session = this.sessionFactory.getCurrentSession();
		Integer id = null;
		try {
			session.beginTransaction();
			id = (Integer) session.save(department); 
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return id; 
	}
	
	public Department get(Integer id) {
		Session session = this.sessionFactory.getCurrentSession();
		Department department = null; 
		try {
			session.beginTransaction();
			department = session.get(Department.class, id); 
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return department; 
	}
	
	public Department getByName(String name) {
		Session session = this.sessionFactory.getCurrentSession();
		Department department = null; 
		try {
			session.beginTransaction();
			Criteria criteria = session.createCriteria(Department.class); 
			department = (Department) criteria.add(Restrictions.eqOrIsNull("name",  name)).uniqueResult();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return department; 
	}
	
	public Boolean update(Integer id, Department department) {
		Department departmentDb = this.get(id); 
		if (departmentDb == null) {
			System.out.println("Error updating Department. Invalid department id: " + id);
			return false;
		} 
		
		Session session = this.sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();

			// update fields 
			departmentDb.setBuilding(department.getBuilding());
			departmentDb.setEmployees(department.getEmployees());
			departmentDb.setName(department.getName());
			session.saveOrUpdate(departmentDb); 
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			return false; 
		} finally {
			session.close();
		}
		return true; 
	}
	
	public Boolean delete(Integer id) {
		Session session = this.sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			Department departmentDb = session.get(Department.class, id); 
			if (departmentDb == null) {
				System.out.println("Error deleting Department. Invalid department id: " + id);
				return false; 
			}
			session.delete(departmentDb); 
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			return false; 
		} finally {
			session.close();
		}
		return true; 
	}
	
	
	
	
	


	
	

}
