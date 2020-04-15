package com.synerzip.dataGenerationTool.base.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tb_cities database table.
 * 
 */
@Entity
@Table(name="tb_cities")
@NamedQuery(name="TbCity.findAll", query="SELECT t FROM TbCity t")
public class TbCity implements Serializable {
	private static final long serialVersionUID = 1L;

	private String city;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="city_id", unique = true, nullable = false)
	private int cityId;

	@Column(name="country_slug")
	private String countrySlug;

	@Column(name="region_slug")
	private String regionSlug;

	public TbCity() {
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getCityId() {
		return this.cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public String getCountrySlug() {
		return this.countrySlug;
	}

	public void setCountrySlug(String countrySlug) {
		this.countrySlug = countrySlug;
	}

	public String getRegionSlug() {
		return this.regionSlug;
	}

	public void setRegionSlug(String regionSlug) {
		this.regionSlug = regionSlug;
	}

}