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
@NamedQuery(name= "select_batch", query ="from Batch"),
@NamedQuery(name= "update_batch", query ="update Batch set bname=:nm where bid=:id"),
@NamedQuery(name ="delete_batch", query= "delete from Batch where bid=:id")
})
public class Batch {
	@Id
	private int bid;
	private String bname;
	@OneToOne(cascade=CascadeType.ALL)
	private Faculty faculty;

	//setter/getter
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public String getBname() {
		return bname;
	}
	public void setBname(String bname) {
		this.bname = bname;
	}
	public Faculty getFaculty() {
		return faculty;
	}
	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}

}


