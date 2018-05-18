package es.upm.dit.geoloc.dao.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table
public class Likes implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private long UserId;
	private int status;
	private int ThoughtId;
	
	
	
	public Likes () {
		
	}

	public int getId() {
		return id;
	}

	public long getUserId() {
		return UserId;
	}



	public void setUserId(long userId) {
		UserId = userId;
	}

	public int getStatus() {
		return status;
	}



	public void setStatus(int status) {
		this.status = status;
	}

	public int getThoughtId() {
		return ThoughtId;
	}

	public void setThoughtId(int thoughtId) {
		ThoughtId = thoughtId;
	}

}