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
@NamedQuery(name= "select_student", query ="from Student"),
@NamedQuery(name= "update_student", query ="update Student set sname=:nm where sid=:id"),
@NamedQuery(name ="delete_student", query= "delete from Student where sid=:id")
})
public class Student{
	@Id
	private int sid;
	private String sname;
	@OneToOne(cascade=CascadeType.ALL)
	private Batch batch;

	//setter/getter
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public Batch getBatch() {
		return batch;
	}
	public void setBatch(Batch batch) {
		this.batch = batch;
	}
}
