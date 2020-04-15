package com.synerzip.dataGenerationTool.base.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tb_countries database table.
 * 
 */
@Entity
@Table(name="tb_countries")
@NamedQuery(name="TbCountry.findAll", query="SELECT t FROM TbCountry t")
public class TbCountry implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="country")
	private String country;

	@Column(name="country_slug")
	private String countrySlug;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id", unique = true, nullable = false)
	private int id;

	public TbCountry() {
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCountrySlug() {
		return this.countrySlug;
	}

	public void setCountrySlug(String countrySlug) {
		this.countrySlug = countrySlug;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

}