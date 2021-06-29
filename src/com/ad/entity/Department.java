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
import java.util.Set; 
import java.util.stream.Collectors;
import java.util.HashSet; 

@Entity
@Table(name = "department")
public class Department extends BaseEntity {

	@Column(name = "name")
	private String name;

	@OneToMany(mappedBy = "department", fetch = FetchType.EAGER)
	private Set<Employee> employees = new HashSet<>(); 

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "building_id", referencedColumnName = "id")
	private Building building;

	@Override
	public String toString() {
		List<String> employeeNames = this.employees.stream().map(x -> x.getName()).collect(Collectors.toList());
		String buildingName = this.building != null ? this.building.getName() : "-"; 
		return "Department [id=" + id + ",name=" + name + ", employees=" + employeeNames + ", building=" + buildingName + "]";
	}

	// adds employee (if not already added)
	public Boolean addEmployee(Employee employee) {
		employee.setDepartment(this);
		return this.employees.add(employee);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}

	public Building getBuilding() {
		return building;
	}

	public void setBuilding(Building building) {
		this.building = building;
	}

}
