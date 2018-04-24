package es.upm.dit.geoloc.dao.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table
public class Thought implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private long UserId;
	private String pensamiento;
	private String tag1;
	private String tag2;
	private String tag3;
	private String tag4;
	private String tag5;
	private double lat;
	private double lng;
	private int likes;
	
	
	
	public Thought () {
		
	}


	public int getId() {
		return id;
	}

	public long getUserId() {
		return UserId;
	}


	public void setUserId(long userId) {
		this.UserId = userId;
	}


	public double getLat() {
		return lat;
	}


	public void setLat(double d) {
		this.lat = d;
	}


	public double getLng() {
		return lng;
	}


	public void setLng(double lng) {
		this.lng = lng;
	}


	public String getPensamiento() {
		return pensamiento;
	}


	public void setPensamiento(String pensamiento) {
		this.pensamiento = pensamiento;
	}


	public String getTag1() {
		return tag1;
	}


	public void setTag1(String tag1) {
		this.tag1 = tag1;
	}


	public String getTag2() {
		return tag2;
	}


	public void setTag2(String tag2) {
		this.tag2 = tag2;
	}


	public String getTag3() {
		return tag3;
	}


	public void setTag3(String tag3) {
		this.tag3 = tag3;
	}


	public String getTag4() {
		return tag4;
	}


	public void setTag4(String tag4) {
		this.tag4 = tag4;
	}


	public String getTag5() {
		return tag5;
	}


	public void setTag5(String tag5) {
		this.tag5 = tag5;
	}


	public int getLikes() {
		return likes;
	}


	public void setLikes(int likes) {
		this.likes = likes;
	}

}