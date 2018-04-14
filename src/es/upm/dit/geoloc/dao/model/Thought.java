package es.upm.dit.geoloc.dao.model;

import java.io.Serializable;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class Thought implements Serializable{

	@Id
	private double id;
	@ManyToOne
	private double userId;
	private String text;
	private String tag;
	//private POINT location;
	//private DATE createdAt;
	
	
	
	public Thought () {
		
	}


	public double getId() {
		return id;
	}
	
	public void setId(double id) {
		this.id = id;
	}


	public String getText() {
		return text;
	}



	public void setText(String text) {
		this.text = text;
	}



	public String getTag() {
		return tag;
	}



	public void setTag(String tag) {
		this.tag = tag;
	}


	public double getUserId() {
		return userId;
	}


	public void setUserId(double userId) {
		this.userId = userId;
	}

}
