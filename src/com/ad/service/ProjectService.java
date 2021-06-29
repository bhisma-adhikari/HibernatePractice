package com.ad.service;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.ad.entity.BaseEntity;
import com.ad.entity.Employee;
import com.ad.entity.Project;

public class ProjectService extends BaseService {

	public ProjectService(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	public Integer insert(Project project) {
		Session session = this.sessionFactory.getCurrentSession();
		Integer id = null;
		try {
			session.beginTransaction();
			// save/update referenced objects 
			for (Employee e : project.getEmployees()) {
				e.getProjects().add(project);
				session.saveOrUpdate(e);
			}
			id = (Integer) session.save(project);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return id;
	}

	public Project get(Integer id) {
		Session session = this.sessionFactory.getCurrentSession();
		Project project = null;
		try {
			session.beginTransaction();
			project = session.get(Project.class, id);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return project;
	}

	public List<Project> getAll() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Project> projects = null;
		try {
			session.beginTransaction();
			Criteria criteria = session.createCriteria(Project.class);
			projects = criteria.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return projects;
	}

	public Project getByName(String name) {
		Session session = this.sessionFactory.getCurrentSession();
		Project project = null;
		try {
			session.beginTransaction();
			Criteria criteria = session.createCriteria(Project.class);
			project = (Project) criteria.add(Restrictions.eqOrIsNull("name", name)).uniqueResult();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return project;
	}

	public Boolean update(Project project) {
		Project projectDb = this.get(project.getId());
		if (projectDb == null) {
			System.out.println("Error updating Project. Invalid project id: " + project.getId());
			return false;
		}
		// update fields
		projectDb.setEmployees(project.getEmployees());
		projectDb.setName(project.getName());

		Session session = this.sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			// update referenced objects 
			for (Employee e : projectDb.getEmployees()) {
				e.getProjects().add(projectDb); 
				session.saveOrUpdate(e);
			}
			// update project 
			session.saveOrUpdate(projectDb);
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
			Project projectDb = session.get(Project.class, id);
			if (projectDb == null) {
				System.out.println("Error deleting Project. Invalid project id: " + id);
				return false;
			}
			session.delete(projectDb);
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
