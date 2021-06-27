package com.ad.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.List; 

@Entity
@Table(name="department")
public class Department extends BaseEntity {
	
	@Column(name="name")
	private String name;
	
	@OneToMany(mappedBy="department", fetch = FetchType.EAGER)
	private List<Employee> employees; 
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "building_id", referencedColumnName = "id")
	private Building building;

	@Override
	public String toString() {
		return "Department [id=" + id + ",name=" + name + ", employees=" + employees + ", building=" + building + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public Building getBuilding() {
		return building;
	}

	public void setBuilding(Building building) {
		this.building = building;
	} 
	
	
	
	
	
	
}
