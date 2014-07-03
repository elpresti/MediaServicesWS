package com.mediaservices.model;

import com.mediaservices.model.Person;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "PROGRAMS")
public class Program {
	@Id
	@GeneratedValue
	private int pgmId;
	private String name;
	private String logoUrl;
	private String url;
	private String urlImgNow;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "PERSON_PROGRAM", joinColumns = @JoinColumn(name = "PERSON_ID"), inverseJoinColumns = @JoinColumn(name = "PROGRAM_ID"))
	private Collection<Person> persons = new ArrayList<Person>();

	public int getPgmId() {
		return pgmId;
	}

	public void setPgmId(int pgmId) {
		this.pgmId = pgmId;
	}

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrlImgNow() {
		return urlImgNow;
	}

	public void setUrlImgNow(String urlImgNow) {
		this.urlImgNow = urlImgNow;
	}

}