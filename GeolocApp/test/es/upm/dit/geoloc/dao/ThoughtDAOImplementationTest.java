package es.upm.dit.geoloc.dao;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import es.upm.dit.geoloc.dao.model.Thought;

public class ThoughtDAOImplementationTest {


	private Thought t1;
	
	@Before
	public void setUp() throws Exception {
		ThoughtDAOImplementation dao = ThoughtDAOImplementation.getInstance();
		Thought t0 = new Thought();
		dao.createThought(t0);
		
		t1 = new Thought();
		t1.setPensamiento("Hace mucho frío");
		t1.setUserId(1);
		t1.setLat(10);
		t1.setLng(20);
		t1.setLikes(5);
		t1.setTag1("November");
		
		System.out.println("t1.id: "+ t1.getId());
		
		
		dao.createThought(t1);
	}

	@After
	public void tearDown() throws Exception {
		ThoughtDAOImplementation dao = ThoughtDAOImplementation.getInstance();
		
		for (int i = 0; i < 1000; i++) {
			if(dao.readThought(i) != null)
			dao.deleteThought(dao.readThought(i));
		}
		dao.deleteThought(dao.readThought(t1.getId()));
	}
	
	@Test
	public void testCreateThought() {

		ThoughtDAOImplementation dao = ThoughtDAOImplementation.getInstance();
		Thought t2 = dao.readThought(t1.getId());
		
		assertEquals(t1.getId(), t2.getId());
		assertEquals(t1.getLikes(), t2.getLikes());
	}


	@Test
	public void testUpdateThought() {
		ThoughtDAOImplementation dao = ThoughtDAOImplementation.getInstance();
		Thought t2 = dao.readThought(t1.getId());
		
		assertEquals((int)t1.getLat(), (int)t2.getLat());
		assertEquals(t1.getPensamiento(), t2.getPensamiento());
		
		t1.setLat(200);
		dao.updateThought(t1);
		
		Thought t3 = dao.readThought(t1.getId());
		assertNotEquals(t3.getLat(), t2.getLat());
		assertEquals(t3.getPensamiento(), t2.getPensamiento());
		
	}

	@Test
	public void testDeleteThought() {
		ThoughtDAOImplementation dao = ThoughtDAOImplementation.getInstance();
		assertNotNull(dao.readThought(t1.getId()));
		
		dao.deleteThought(t1);
		
		assertNull(dao.readThought(t1.getId()));
	}

	@Test
	public void testGetAll() {
		ThoughtDAOImplementation dao = ThoughtDAOImplementation.getInstance();
		
		List<Thought> ArrayThought = new ArrayList<Thought>();		
		ArrayThought = dao.getAll();	
		assertEquals(2, ArrayThought.size());
		
		Thought t2 = new Thought();
		t2.setPensamiento("Hace mucho mucho frío");
		t2.setUserId(2);
		t2.setLat(30);
		t2.setLng(50);
		t2.setLikes(3);
		t2.setTag1("December");
		
		dao.createThought(t2);
		ArrayThought = dao.getAll();	
		assertEquals(3, ArrayThought.size());
	}

	@Test
	public void testGetPopular() {
		ThoughtDAOImplementation dao = ThoughtDAOImplementation.getInstance();
		
		List<Thought> ArrayThought = new ArrayList<Thought>();		
		ArrayThought = dao.getPopular();	
		assertEquals(0, ArrayThought.size());
		
		Thought t2 = new Thought();
		t2.setPensamiento("Hace mucho mucho frío");
		t2.setUserId(2);
		t2.setLat(30);
		t2.setLng(50);
		t2.setLikes(200);
		t2.setTag1("December");
		
		dao.createThought(t2);
		ArrayThought = dao.getPopular();	
		assertEquals(1, ArrayThought.size());
		
		Thought t3 = new Thought();
		t3.setPensamiento("Hacer mucho mucho frío");
		t3.setUserId(2);
		t3.setLat(40);
		t3.setLng(20);
		t3.setLikes(50);
		t3.setTag1("Decemberrr");
		
		dao.createThought(t3);
		ArrayThought = dao.getPopular();	
		assertEquals(1, ArrayThought.size());
	}

	@Test
	public void testGetTags() {
		ThoughtDAOImplementation dao = ThoughtDAOImplementation.getInstance();
		List<Thought> ArrayThought = new ArrayList<Thought>();		
		ArrayThought = dao.getTags("retiro");	
		assertEquals(0, ArrayThought.size());
		
		Thought t3 = new Thought();
		t3.setTag1("retiro");
		
		dao.createThought(t3);
		
		ArrayThought = dao.getTags("retiro");	
		assertEquals(1, ArrayThought.size());
		
		Thought t2 = new Thought();
		t2.setPensamiento("Hace fresco");
		t2.setUserId(2);
		t2.setLat(30);
		t2.setLng(50);
		t2.setLikes(200);
		t2.setTag3("retiro");
		
		dao.createThought(t2);
		ArrayThought = dao.getTags("retiro");	
		assertEquals(2, ArrayThought.size());
		
		dao.deleteThought(t2);
		ArrayThought = dao.getTags("retiro");	
		assertEquals(1, ArrayThought.size());
		
	}

	@Test
	public void testGetTagsPopulares() {
		ThoughtDAOImplementation dao = ThoughtDAOImplementation.getInstance();
		List<Thought> ArrayThought = new ArrayList<Thought>();		
		ArrayThought = dao.getTagsPopulares("retiro");	
		assertEquals(0, ArrayThought.size());
		
		Thought t3 = new Thought();
		t3.setTag1("retiro");
		t3.setLikes(200);
		
		dao.createThought(t3);
		
		ArrayThought = dao.getTagsPopulares("retiro");	
		assertEquals(1, ArrayThought.size());
		
		Thought t2 = new Thought();
		t2.setPensamiento("Hace fresco");
		t2.setUserId(2);
		t2.setLat(30);
		t2.setLng(50);
		t2.setLikes(20);
		t2.setTag3("retiro");
		
		dao.createThought(t2);
		ArrayThought = dao.getTagsPopulares("retiro");	
		assertEquals(1, ArrayThought.size());
		
		
		dao.deleteThought(t3);
		ArrayThought = dao.getTagsPopulares("retiro");	
		assertEquals(0, ArrayThought.size());
	}

	@Test
	public void testGetTagsMisMarcadores() {
		ThoughtDAOImplementation dao = ThoughtDAOImplementation.getInstance();
		List<Thought> ArrayThought = new ArrayList<Thought>();		
		ArrayThought = dao.getTagsMisMarcadores("retiro",1);	
		assertEquals(0, ArrayThought.size());
		
		Thought t2 = new Thought();
		t2.setPensamiento("Hace fresco");
		t2.setUserId(1);
		t2.setLat(30);
		t2.setLng(50);
		t2.setLikes(20);
		t2.setTag3("retiro");
		
		dao.createThought(t2);
		ArrayThought = dao.getTagsMisMarcadores("retiro",1);	
		assertEquals(1, ArrayThought.size());
		
		
	}

	@Test
	public void testGetMisMarcadores() {
		ThoughtDAOImplementation dao = ThoughtDAOImplementation.getInstance();
		List<Thought> ArrayThought = new ArrayList<Thought>();		
		ArrayThought = dao.getMisMarcadores(1);
		assertEquals(1, ArrayThought.size());
		
		ArrayThought = dao.getMisMarcadores(2);
		assertEquals(0, ArrayThought.size());
		
		dao.deleteThought(t1);
		
		ArrayThought = dao.getMisMarcadores(1);
		assertEquals(0, ArrayThought.size());
		
		Thought t2 = new Thought();
		t2.setPensamiento("Hace fresco");
		t2.setUserId(2);
		t2.setLat(30);
		t2.setLng(50);
		t2.setLikes(20);
		t2.setTag3("retiro");
		
		dao.createThought(t2);
		ArrayThought = dao.getMisMarcadores(1);
		assertEquals(0, ArrayThought.size());
		
		ArrayThought = dao.getMisMarcadores(2);
		assertEquals(1, ArrayThought.size());
		

	}


	

}
