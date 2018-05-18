	package es.upm.dit.geoloc.dao;

	import java.util.List;

import es.upm.dit.geoloc.dao.model.Likes;

public interface LikesDAO {
		public void createLikes(Likes likes);
		public Likes readLikes(int id, long UserId);
		public void updateChat(Likes likes);
		public void deleteChat(Likes likes);
		public List<Likes> getMisLikes(long UserId);
		public void changeStatus(int id, long UserId);
		void changeStatus2(int id, long UserId);
}