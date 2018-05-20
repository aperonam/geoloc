package es.upm.dit.geoloc.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import es.upm.dit.geoloc.dao.model.Likes;

public class LikesDAOImplementationTest {
	
	Likes like;
	
	@Before
	public void setUp() throws Exception {
		like = new Likes();
		like.setUserId(1);
		like.setThoughtId(1);
		like.setStatus(1);
		LikesDAOImplementation.getInstance().createLikes(like);
	}
	
	@After
	public void tearDown() throws Exception {
		LikesDAOImplementation.getInstance().delete(like);
		like = null;
	}

	@Test
	public void testCreateLikes() {
		Likes like = LikesDAOImplementation.getInstance().readLikes(this.like.getThoughtId(), this.like.getUserId());
		assertEquals(like.getThoughtId(), this.like.getThoughtId());
		assertEquals(like.getUserId(), this.like.getUserId());
		assertEquals(like.getStatus(), this.like.getStatus());
	}

	@Test
	public void testReadLikes() {
		Likes like = LikesDAOImplementation.getInstance().readLikes(this.like.getThoughtId(), this.like.getUserId());
		assertEquals(like.getThoughtId(), this.like.getThoughtId());
		assertEquals(like.getUserId(), this.like.getUserId());
		assertEquals(like.getStatus(), this.like.getStatus());
	}

	@Test
	public void testGetMisLikes() {
		List<Likes> likes = LikesDAOImplementation.getInstance().getMisLikes(this.like.getUserId());
		assertEquals(likes.size(), 1);
	}

	@Test
	public void testChangeStatus() {
		LikesDAOImplementation.getInstance().changeStatus(this.like.getThoughtId(), this.like.getUserId());
		Likes like = LikesDAOImplementation.getInstance().readLikes(this.like.getThoughtId(), this.like.getUserId());
		assertEquals(like.getStatus(), 2);
	}

	@Test
	public void testChangeStatus2() {
		LikesDAOImplementation.getInstance().changeStatus2(this.like.getThoughtId(), this.like.getUserId());
		Likes like = LikesDAOImplementation.getInstance().readLikes(this.like.getThoughtId(), this.like.getUserId());
		assertEquals(like.getStatus(), 1);
	}
	
	@Test
	public void testDelete() {
		Likes like2 = new Likes();
		like2.setUserId(1);
		like2.setThoughtId(2);
		like2.setStatus(1);
		
		LikesDAOImplementation.getInstance().createLikes(like2);
		Likes like3 = LikesDAOImplementation.getInstance().readLikes(2, 1);
		assertNotNull(like3);
		
		LikesDAOImplementation.getInstance().delete(like3);
		Likes like4 = LikesDAOImplementation.getInstance().readLikes(2, 1);
		assertNull(like4);
	}

}