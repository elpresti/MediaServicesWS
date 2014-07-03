package com.mediaservices.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
// @Inheritance(strategy=InheritanceType.JOINED)
@Table(name = "RADIOSTATIONS")
public class RadioStation extends Medium {

	private ArrayList<String> videoStreams;
	private ArrayList<String> audioStreams;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "PROGRAM_RADIOSTATION", joinColumns = @JoinColumn(name = "PROGRAM_ID"), inverseJoinColumns = @JoinColumn(name = "RADIOSTATION_ID"))
	private Collection<Program> programs = new ArrayList<Program>();

	public ArrayList<String> getAudioStreams() {
		return audioStreams;
	}

	public void setAudioStreams(ArrayList<String> audioStreams) {
		this.audioStreams = audioStreams;
	}

	public ArrayList<String> getVideoStreams() {
		return videoStreams;
	}

	public void setVideoStreams(ArrayList<String> videoStreams) {
		this.videoStreams = videoStreams;
	}

}