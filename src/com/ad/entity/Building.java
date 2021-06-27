package com.ad.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import java.util.Date; 

@Entity 
@Table(name="building")
public class Building extends BaseEntity{
	@Column(name="name")
	private String name; 
	
	@Column(name="estd")
	private Date estd; 
	
	@OneToOne(mappedBy = "building")
	private Department department;

	@Override
	public String toString() {
		return "Building [id=" + id + ",name=" + name + ", estd=" + estd + ", department=" + department.getName() + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getEstd() {
		return estd;
	}

	public void setEstd(Date estd) {
		this.estd = estd;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	} 
	
	
}
