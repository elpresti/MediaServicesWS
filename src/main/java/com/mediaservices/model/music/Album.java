package com.mediaservices.model.music;

import java.util.Date;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="ALBUMS")
public class Album {
	private String name;
	private Date date;
	private ArrayList<Artist> authors;
	private ArrayList<Song> songs;

	public ArrayList<Artist> getAuthors() {
		return authors;
	}

	public void setAuthors(ArrayList<Artist> authors) {
		this.authors = authors;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Song> getSongs() {
		return songs;
	}

	public void setSongs(ArrayList<Song> songs) {
		this.songs = songs;
	}

}