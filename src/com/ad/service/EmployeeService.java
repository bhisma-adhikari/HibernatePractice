package com.ad.service;

import org.hibernate.Criteria;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.ad.entity.BaseEntity;
import com.ad.entity.Employee;
import com.ad.entity.Project;

public class EmployeeService extends BaseService {

	public EmployeeService(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	public Integer insert(Employee employee) {
		Session session = this.sessionFactory.getCurrentSession();
		Integer id = null;
		try {
			session.beginTransaction();
			// save/update referenced objects
			if (employee.getDepartment() != null) {
				employee.getDepartment().addEmployee(employee); 
				session.saveOrUpdate(employee.getDepartment());
			}

			for (Project p : employee.getProjects()) {
				p.getEmployees().add(employee); 
				session.saveOrUpdate(p);
			}
			// save employee
			id = (Integer) session.save(employee);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return id;
	}

	public Employee get(Integer id) {
		Session session = this.sessionFactory.getCurrentSession();
		Employee employee = null;
		try {
			session.beginTransaction();
			employee = session.get(Employee.class, id);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return employee;
	}

	public List<Employee> getAll() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Employee> employees = null;
		try {
			session.beginTransaction();
			Criteria criteria = session.createCriteria(Employee.class);
			employees = criteria.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return employees;
	}

	public Employee getByUniqueId(String uniqueId) {
		Session session = this.sessionFactory.getCurrentSession();
		Employee employee = null;
		try {
			session.beginTransaction();
			Criteria criteria = session.createCriteria(Employee.class);
			employee = (Employee) criteria.add(Restrictions.eqOrIsNull("uniqueId", uniqueId)).uniqueResult();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return employee;
	}

	public Boolean update(Employee employee) {
		Employee employeeDb = this.get(employee.getId());
		if (employeeDb == null) {
			System.out.println("Error updating Employee. Invalid employee id: " + employee.getId());
			return false;
		}
		// update fields
		employeeDb.setAge(employee.getAge());
		employeeDb.setDepartment(employee.getDepartment());
		employeeDb.setName(employee.getName());
		employeeDb.setProjects(employee.getProjects());
		employeeDb.setSalary(employee.getSalary());
		employeeDb.setUniqueId(employee.getUniqueId());

		Session session = this.sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			// update referenced objects
			employeeDb.getDepartment().addEmployee(employeeDb);
			session.saveOrUpdate(employeeDb.getDepartment());
			for (Project p : employeeDb.getProjects()) {
				p.getEmployees().add(employeeDb); 
				session.saveOrUpdate(p);
			}
			// update employee
			session.saveOrUpdate(employeeDb);
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
			Employee employeeDb = session.get(Employee.class, id);
			if (employeeDb == null) {
				System.out.println("Error deleting Employee. Invalid employee id: " + id);
				return false;
			}
			session.delete(employeeDb);
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
