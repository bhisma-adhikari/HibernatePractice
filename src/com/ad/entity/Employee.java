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

@Entity
@Table(name = "employee")
public class Employee extends BaseEntity {
	@Column(name = "name")
	private String name;

	@Column(name = "age")
	private Integer age;

	@Column(name = "salary")
	private Double salary;

	@ManyToOne
	@JoinColumn (name = "department_id", nullable=false)
	private Department department;

	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(
			name = "employee_project",
			joinColumns = { @JoinColumn(name = "employee_id") },
			inverseJoinColumns = { @JoinColumn(name = "project_id") })
	private List<Project> projects;

	@Override
	public String toString() {
		return "Employee [id=" + id + ",name=" + name + ", age=" + age + ", salary=" + salary + ", department=" + department
				+ ", projects=" + projects + "]";
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

	public List<Project> getProjects() {
		return projects;
	}

	public void setRoles(List<Project> projects) {
		this.projects = projects;
	}

}
