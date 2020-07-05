package com.ghriit.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cache(usage =CacheConcurrencyStrategy.READ_WRITE)
// All queries
@NamedQueries({
@NamedQuery(name= "select_course", query ="from Course"),
@NamedQuery(name= "update_course", query ="update Course set cname=:nm where cid=:id"),
@NamedQuery(name ="delete_course", query= "delete from Course where cid=:id")
})
public class Course {
	@Id
	private int cid;
	private String cname;

	//setter/getter
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
}
