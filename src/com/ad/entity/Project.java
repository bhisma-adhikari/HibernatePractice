package com.ad.entity;

import javax.persistence.Column;
import java.util.HashSet; 
import java.util.Set; 
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.stream.Collectors; 

@Entity 
@Table(name="project")
public class Project extends BaseEntity {
	@Column(name="name")
	private String name; 
	
	@ManyToMany(mappedBy = "projects", fetch = FetchType.EAGER)
	private Set<Employee> employees = new HashSet<>();

	@Override
	public String toString() {
		List<String> employeeNames = this.employees.stream().map(x -> x.getName()).collect(Collectors.toList()); 
		return "Project [id=" + id + ",name=" + name + ", employees=" + employeeNames + "]";
	}
	
	// adds employee (if not already added)
	public Boolean addEmployee(Employee employee) {
		return this.employees.add(employee); 
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ManyToMany(mappedBy = "projects")
	public Set<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	} 
	
	
}
