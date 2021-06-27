package com.ad.main;

import java.util.Date;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.ad.entity.Building;
import com.ad.entity.Department;
import com.ad.entity.Employee;
import com.ad.entity.Project;
import com.ad.service.BuildingService;
import com.ad.service.DepartmentService;

public class Main {
	public static void main(String[] args) {
		SessionFactory sf = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Department.class)
				.addAnnotatedClass(Employee.class)
				.addAnnotatedClass(Project.class)
				.addAnnotatedClass(Building.class) 
				.buildSessionFactory();
		
		BuildingService buildingService = new BuildingService(sf); 
		DepartmentService departmentService = new DepartmentService(sf); 
		
//		// INSERT BUILDING
//		Building building = new Building(); 
//		building.setName("Hughes Hall");
//		building.setEstd(new Date(1850, 2, 15));
//		System.out.println(buildingService.insert(building));
		
//		// FETCH BUILDING
//		Building building = buildingService.getByName("Benton Hall");
//		System.out.println(building);

		// UPDATE BUILDING 
//		Building building = buildingService.getByName("Hughes Hall");
//		building.setEstd(new Date(1999, 1, 13));
//		buildingService.update(building.getId(), building); 

//		// DELETE BUILDING 
//		System.out.println(buildingService.delete(7));
		
		
//		// INSERT DEPARTMENT 
//		Department department = new Department(); 
//		department.setBuilding(buildingService.getByName("Hughes Hall"));
//		department.setName("Chemistry");
//		System.out.println(departmentService.insert(department)); 
		
//		// FETCH DEPARTMENT 
//		Department department = departmentService.getByName("Chemistry"); 
//		System.out.println(department);
		
//		// UPDATE DEPARTMENT 
//		Department department = departmentService.getByName("Chemistry"); 
//		department.setName("Bio Chemistry");
//		departmentService.update(department.getId(), department); 
		
//		// DELETE DEPARTMENT 
//		System.out.println(departmentService.delete(1));
		
	}
}
