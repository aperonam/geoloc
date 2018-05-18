package es.upm.dit.geoloc.dao.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.gson.JsonObject;

@Entity
@Table
public class Message implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private long UserId;
	private String text;
	private int ChatId;
	private String createdAt;
	
	
	
	public 	Message () {
		
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

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getChatId() {
		return ChatId;
	}

	public void setChatId(int chatId) {
		ChatId = chatId;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	
	public JsonObject getJsonObject () {
		JsonObject jo = new JsonObject();
		
		jo.addProperty("UserId", this.getUserId());
		jo.addProperty("text", this.getText());
		jo.addProperty("ChatId", this.getChatId());
		jo.addProperty("createdAt", this.getCreatedAt());
		
		return jo;
	}

}