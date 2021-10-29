package com.douzone.jblog.vo;

import java.sql.Date;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

public class UserVo {
	
	
	@NotEmpty
	@Length(min=2, max=8)
	private String id;
	
	@NotEmpty
	@Length(min=2,max=8)
	private String name;
	
	@NotEmpty
	@Length(min=4,max=16)
	private String password;
	
	private Date join_date;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getJoin_date() {
		return join_date;
	}
	public void setJoin_date(Date join_date) {
		this.join_date = join_date;
	}
	
	
}
