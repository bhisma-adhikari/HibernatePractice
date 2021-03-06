package com.ad.service;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.ad.entity.BaseEntity;
import com.ad.entity.Building;

public class BuildingService extends BaseService {

	public BuildingService(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	public Integer insert(Building building) {
		Session session = this.sessionFactory.getCurrentSession();
		Integer id = null;
		try {
			session.beginTransaction();
			// save/update referenced objects
			if (building.getDepartment() != null) {
				building.getDepartment().setBuilding(building);
				session.saveOrUpdate(building.getDepartment());
			}
			// save building
			id = (Integer) session.save(building);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return id;
	}

	public Building get(Integer id) {
		Session session = this.sessionFactory.getCurrentSession();
		Building building = null;
		try {
			session.beginTransaction();
			building = session.get(Building.class, id);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return building;
	}

	public List<Building> getAll() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Building> buildings = null;
		try {
			session.beginTransaction();
			Criteria criteria = session.createCriteria(Building.class);
			buildings = criteria.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return buildings;
	}

	public Building getByName(String name) {
		Session session = this.sessionFactory.getCurrentSession();
		Building building = null;
		try {
			session.beginTransaction();
			Criteria criteria = session.createCriteria(Building.class);
			building = (Building) criteria.add(Restrictions.eqOrIsNull("name", name)).uniqueResult();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return building;
	}

	public Boolean update(Building building) {
		Building buildingDb = this.get(building.getId());
		if (buildingDb == null) {
			System.out.println("Error updating Building. Invalid building id: " + building.getId());
			return false;
		}
		// update fields
		buildingDb.setDepartment(building.getDepartment());
		buildingDb.setEstd(building.getEstd());
		building.setName(building.getName());

		Session session = this.sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			// update referenced objects
			buildingDb.getDepartment().setBuilding(buildingDb);
			session.saveOrUpdate(buildingDb.getDepartment());
			// update building
			session.saveOrUpdate(buildingDb);
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
			Building buildingDb = session.get(Building.class, id);
			if (buildingDb == null) {
				System.out.println("Error deleting Building. Invalid building id: " + id);
				return false;
			}
			session.delete(buildingDb);
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
