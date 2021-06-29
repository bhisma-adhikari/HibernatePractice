package com.ad.main;

import java.util.Date;
import java.util.*;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.ad.entity.Building;
import com.ad.entity.Department;
import com.ad.entity.Employee;
import com.ad.entity.Project;
import com.ad.service.BuildingService;
import com.ad.service.DepartmentService;
import com.ad.service.EmployeeService;
import com.ad.service.ProjectService;

public class Main {
	private static SessionFactory sf = new Configuration()
			.configure("hibernate.cfg.xml")
			.addAnnotatedClass(Department.class)
			.addAnnotatedClass(Employee.class)
			.addAnnotatedClass(Project.class)
			.addAnnotatedClass(Building.class)
			.buildSessionFactory();

	private static BuildingService buildingService = new BuildingService(sf);
	private static DepartmentService departmentService = new DepartmentService(sf);
	private static ProjectService projectService = new ProjectService(sf);
	private static EmployeeService employeeService = new EmployeeService(sf);

	public static void main(String[] args) {
//		emptyDatabase();
		populateDatabase();
	}
	
	// has issues 
	public static void emptyDatabase() {
		
		for (Department d : departmentService.getAll()) {
			departmentService.delete(d.getId()); 
		}
		
		for (Building b : buildingService.getAll()) {
			buildingService.delete(b.getId());
		}
		
		
		for (Employee e : employeeService.getAll()) {
			employeeService.delete(e.getId());
		}
		
		for (Project p : projectService.getAll()) {
			projectService.delete(p.getId()); 
		}
	}

	public static void populateDatabase() {
		// INSERT BUILDING
		Building building = new Building();
		building.setName("Benton Hall");
		building.setEstd(new Date(1850, 12, 15));
		buildingService.insert(building);

		building = new Building();
		building.setName("Hughes Hall");
		building.setEstd(new Date(1900, 2, 21));
		buildingService.insert(building);

		// INSERT DEPARTMENT
		Department department = new Department();
		department.setBuilding(buildingService.getByName("Benton Hall"));
		department.setName("Computer Science");
		departmentService.insert(department);

		department = new Department();
		department.setBuilding(buildingService.getByName("Hughes Hall"));
		department.setName("Chemistry");
		departmentService.insert(department);

		// INSERT EMPLOYEE
		Employee employee = new Employee();
		employee.setAge(26);
		employee.setDepartment(departmentService.getByName("Chemistry"));
		employee.setName("Freeman Morgan");
		employee.setSalary(60000.00);
		employee.setUniqueId("morganf");
		employeeService.insert(employee);

		employee = new Employee();
		employee.setAge(22);
		employee.setDepartment(departmentService.getByName("Chemistry"));
		employee.setName("Jun Lee");
		employee.setSalary(60000.00);
		employee.setUniqueId("leej");
		employeeService.insert(employee);

		employee = new Employee();
		employee.setAge(27);
		employee.setDepartment(departmentService.getByName("Computer Science"));
		employee.setName("Matthew Stephan");
		employee.setSalary(90000.00);
		employee.setUniqueId("stephamd");
		employeeService.insert(employee);

		employee = new Employee();
		employee.setAge(27);
		employee.setDepartment(departmentService.getByName("Computer Science"));
		employee.setName("Eric Rapos");
		employee.setSalary(96000.00);
		employee.setUniqueId("rapose");
		employeeService.insert(employee);

		employee = new Employee();
		employee.setAge(28);
		employee.setDepartment(departmentService.getByName("Computer Science"));
		employee.setName("Bhisma Adhikari");
		employee.setSalary(55000.00);
		employee.setUniqueId("adhikab");
		employeeService.insert(employee);

		// INSERT PROJECT
		Project project = new Project();
		project.setName("SimIMA");
		Set<Employee> employees = new HashSet<>();
		employees.add(employeeService.getByUniqueId("stephamd"));
		employees.add(employeeService.getByUniqueId("adhikab"));
		project.setEmployees(employees);
		projectService.insert(project);

		project = new Project();
		project.setName("IML");
		employees = new HashSet<>();
		employees.add(employeeService.getByUniqueId("stephamd"));
		employees.add(employeeService.getByUniqueId("rapose"));
		project.setEmployees(employees);
		projectService.insert(project);

		project = new Project();
		project.setName("CarbonPrj");
		employees = new HashSet<>();
		employees.add(employeeService.getByUniqueId("morganf"));
		employees.add(employeeService.getByUniqueId("leej"));
		project.setEmployees(employees);
		projectService.insert(project);

	}
}
