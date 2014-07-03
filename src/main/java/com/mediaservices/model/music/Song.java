package com.mediaservices.model.music;

import java.util.ArrayList;

import javax.persistence.Entity;

@Entity
public class Song {
	private String title;
	private Artist artists;
	private Album album;
	private String genre;
	private ArrayList<Artist> authors;
	private int trackId;
	private Lyric lyric;

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	public Artist getArtists() {
		return artists;
	}

	public void setArtists(Artist artists) {
		this.artists = artists;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public Lyric getLyric() {
		return lyric;
	}

	public void setLyric(Lyric lyric) {
		this.lyric = lyric;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getTrackId() {
		return trackId;
	}

	public void setTrackId(int trackId) {
		this.trackId = trackId;
	}

	public ArrayList<Artist> getAuthors() {
		return authors;
	}

	public void setAuthors(ArrayList<Artist> authors) {
		this.authors = authors;
	}  

}