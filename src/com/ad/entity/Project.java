package com.ad.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List; 

@Entity 
@Table(name="project")
public class Project extends BaseEntity {
	@Column(name="name")
	private String name; 
	
	@ManyToMany(mappedBy = "projects")
	private List<Employee> employees;

	@Override
	public String toString() {
		return "Role [id=" + id + ",name=" + name + ", employees=" + employees + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ManyToMany(mappedBy = "projects")
	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	} 
	
	
}
