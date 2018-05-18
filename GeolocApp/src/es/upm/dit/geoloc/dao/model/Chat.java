package es.upm.dit.geoloc.dao.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table
public class Chat implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private long UserId1;
	private long UserId2;
	private int status;
	private int ThoughtId;
	
	
	
	public Chat () {
		
	}

	public int getId() {
		return id;
	}

	public long getUserId1() {
		return UserId1;
	}



	public void setUserId1(long userId1) {
		UserId1 = userId1;
	}



	public long getUserId2() {
		return UserId2;
	}



	public void setUserId2(long userId2) {
		UserId2 = userId2;
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