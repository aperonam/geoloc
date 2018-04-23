package es.upm.dit.geoloc.dao.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.vividsolutions.jts.geom.Point;

@Entity
public class Thought implements Serializable {

	@Id
	private int id;
	@ManyToOne
	private double userId;
	private String text;
	private String tag;
	private double latitude;
	private double longitude;
	// private DATE createdAt;
	
	
	public Thought () {
		
	}


	public int getId() {
		return id;
	}
	
	public void setId(int id) {
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


	public double getLatitude() {
		return latitude;
	}


	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}


	public double getLongitude() {
		return longitude;
	}


	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

}
