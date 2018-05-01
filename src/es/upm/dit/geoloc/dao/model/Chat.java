package es.upm.dit.geoloc.dao.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Chat implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	private User user;
	@ManyToOne
	@JoinColumn(name = "to_user_id")
	private User toUser;
	@ManyToOne
	private Thought thought;
	private boolean accepted;
	
	public Chat() {}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public User getToUser() {
		return toUser;
	}
	
	public void setToUser(User toUser) {
		this.toUser = toUser;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public Thought getThought() {
		return thought;
	}
	
	public void setThought(Thought thought) {
		this.thought = thought;
	}
	
	public boolean getAccepted() {
		return accepted;
	}
	
	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}
	
}
