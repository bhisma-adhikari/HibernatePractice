package com.ad.entity;

import javax.persistence.Entity;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import java.util.List;
import java.util.Set;
import java.util.HashSet; 
import java.util.stream.Collectors;

@Entity
@Table(name = "employee")
public class Employee extends BaseEntity {
	@Column(name = "unique_id")
	private String uniqueId;

	@Column(name = "name")
	private String name;

	@Column(name = "age")
	private Integer age;

	@Column(name = "salary")
	private Double salary;

	@ManyToOne
	@JoinColumn(name = "department_id", nullable = false)
	private Department department;

	@ManyToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	@JoinTable(
			name = "employee_project",
			joinColumns = { @JoinColumn(name = "employee_id") },
			inverseJoinColumns = { @JoinColumn(name = "project_id") })
	private Set<Project> projects = new HashSet<>(); 

	@Override
	public String toString() {
		List<String> projectNames = this.projects.stream().map(x -> x.getName()).collect(Collectors.toList()); 
		String departmentName = this.department != null ? this.department.getName() : "-"; 
		return "Employee [id=" + id + ",uniqueId=" + uniqueId + ",name=" + name + ", age=" + age + ", salary=" + salary + ", department=" + departmentName + ", projects=" + projectNames + "]";
	}
	
	// adds project (if not already added)
		public Boolean addProject(Project project) {
			return this.projects.add(project); 
		}

	public String getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Set<Project> getProjects() {
		return projects;
	}

	public void setProjects(Set<Project> projects) {
		this.projects = projects;
	}

}
