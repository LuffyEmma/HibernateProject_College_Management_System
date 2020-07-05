package com.ghriit.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cache(usage =CacheConcurrencyStrategy.READ_WRITE)
//All queries
@NamedQueries({
@NamedQuery(name= "select_faculty", query ="from Faculty"),
@NamedQuery(name= "update_faculty", query ="update Faculty set fname=:nm where fid=:id"),
@NamedQuery(name ="delete_faculty", query= "delete from Faculty where fid=:id")
})
public class Faculty{
	@Id
	private int fid;
	private String fname;
	@OneToOne(cascade=CascadeType.ALL)
	private Course course;

	//setter/getter
	public int getFid() {
		return fid;
	}
	public void setFid(int fid) {
		this.fid = fid;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
}



