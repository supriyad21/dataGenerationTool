package com.synerzip.dataGenerationTool.base.model;

import java.io.Serializable;
import javax.persistence.*;



/**
 * The persistent class for the tb_mytb database table.
 * 
 */
@Entity
@Table(name="tb_mytb")
@NamedQuery(name="TbMytb.findAll", query="SELECT t FROM TbMytb t")
public class TbMytb implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "ct")
	private String ct;

	@Column(name = "email")
	
	private String email;



	@Column(name = "name")
	
	private String name;

	public TbMytb(String ct, String email, String name, String ph) {
		super();
		this.ct = ct;
		this.email = email;
		this.name = name;
		this.ph = ph;
	}

	@Override
	public String toString() {
		return "TbMytb [ id=" + id + ", ct=" + ct + ", email=" + email + ",  name=" + name + ", ph=" + ph + "]";
	}

	@Column(name ="ph")
	private String ph;

	
	public TbMytb() {
	}

	public String getCt() {
		return this.ct;
	}

	public void setCt(String ct) {
		this.ct = ct;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPh() {
		return this.ph;
	}

	public void setPh(String ph) {
		this.ph = ph;
	}

}