package org.sebapresti.mediaservices.model;

import java.util.Date;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
public class Message {

	private long id;
	private String message;
	private Date createdat;
	private String author;
	private Map<Long,Comment> comments;
	
	public Message(){
		
	}
	
	public Message(long id,String message,String author){
		this.id=id;
		this.message=message;
		this.createdat=new Date();
		this.author=author;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getCreatedat() {
		return createdat;
	}
	public void setCreatedat(Date createdat) {
		this.createdat = createdat;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
	@XmlTransient //anotation to exclude this field while building the XML or JSON output of this class
	public Map<Long,Comment> getComments(){
		return comments;
	}
	
	public void setComments(Map<Long,Comment> comments){
		this.comments = comments;
	}
}
