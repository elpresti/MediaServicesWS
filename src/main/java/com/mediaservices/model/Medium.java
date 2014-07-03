package com.mediaservices.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;

@Entity
@Table(name = "MEDIUMS")
public abstract class Medium {
	@Id
	@GeneratedValue
	private int mediumId;
	private String name;
	private String country;
	private String state;
	private String city;
	private String website;
	private String fullAddress;
	private String logoUrl;
	private String shortText;
	private String fullDescription;
	private String urlImgNow;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "MEDIUM_SOCIALNETWORK", joinColumns = @JoinColumn(name = "MEDIUM_ID"), inverseJoinColumns = @JoinColumn(name = "SOCIALNET_ID"))
	private Collection<Medium> socialNetworks = new ArrayList<Medium>();

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getFullAddress() {
		return fullAddress;
	}

	public void setFullAddress(String fullAddress) {
		this.fullAddress = fullAddress;
	}

	public String getFullDescription() {
		return fullDescription;
	}

	public void setFullDescription(String fullDescription) {
		this.fullDescription = fullDescription;
	}

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	public int getMediumId() {
		return mediumId;
	}

	public void setMediumId(int mediumId) {
		this.mediumId = mediumId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortText() {
		return shortText;
	}

	public void setShortText(String shortText) {
		this.shortText = shortText;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getUrlImgNow() {
		return urlImgNow;
	}

	public void setUrlImgNow(String urlImgNow) {
		this.urlImgNow = urlImgNow;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

}