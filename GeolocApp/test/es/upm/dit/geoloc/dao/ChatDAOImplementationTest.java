package es.upm.dit.geoloc.dao;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import es.upm.dit.geoloc.dao.model.Chat;
import es.upm.dit.geoloc.dao.model.Likes;

public class ChatDAOImplementationTest {
	
	Chat chat;
	
	@Before
	public void setUp() throws Exception {
		chat = new Chat();
		chat.setUserId1(1);
		chat.setUserId2(2);
		chat.setThoughtId(1);
		chat.setStatus(1);
		chat = ChatDAOImplementation.getInstance().create(chat);
	}
	
	@After
	public void tearDown() throws Exception {
		ChatDAOImplementation.getInstance().deleteChat(chat);
		chat = null;
	}

	@Test
	public void testCreateChat() {
		Chat chat = ChatDAOImplementation.getInstance().readChat(this.chat.getId());
		assertEquals(chat.getThoughtId(), this.chat.getThoughtId());
		assertEquals(chat.getUserId1(), this.chat.getUserId1());
		assertEquals(chat.getUserId2(), this.chat.getUserId2());
	}

	@Test
	public void testReadChat() {
		Chat chat = ChatDAOImplementation.getInstance().readChat(this.chat.getId());
		assertEquals(chat.getThoughtId(), this.chat.getThoughtId());
		assertEquals(chat.getUserId1(), this.chat.getUserId1());
		assertEquals(chat.getUserId2(), this.chat.getUserId2());
	}

	@Test
	public void testUpdateChat() {
		Chat chat = ChatDAOImplementation.getInstance().readChat(this.chat.getId());
		chat.setStatus(4);
		ChatDAOImplementation.getInstance().updateChat(chat);
		
		Chat chat2 = ChatDAOImplementation.getInstance().readChat(this.chat.getId());
		assertEquals(chat.getStatus(), chat2.getStatus());
		
		chat2.setStatus(1);
		ChatDAOImplementation.getInstance().updateChat(chat2);
	}

	@Test
	public void testDeleteChat() {
		Chat chat2 = new Chat();
		chat.setUserId1(4);
		chat.setUserId2(8);
		chat.setThoughtId(4);
		chat.setStatus(1);
		
		Chat chat3 = ChatDAOImplementation.getInstance().create(chat2);
		assertNotNull(chat3);
		
		ChatDAOImplementation.getInstance().deleteChat(chat3);
		Chat chat4 = ChatDAOImplementation.getInstance().readChat(chat3.getId());
		assertNull(chat4);
	}

	

	@Test
	public void testGetStatus() {
		int status = ChatDAOImplementation.getInstance().getStatus(this.chat.getId());
		assertEquals(0, status);
	}

	@Test
	public void testChangeStatus() {
		ChatDAOImplementation.getInstance().changeStatus(this.chat.getId());
		int status = ChatDAOImplementation.getInstance().getStatus(this.chat.getId());
		assertEquals(0, status);
	}

}