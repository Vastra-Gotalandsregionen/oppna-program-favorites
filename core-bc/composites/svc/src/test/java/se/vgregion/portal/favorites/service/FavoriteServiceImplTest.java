package se.vgregion.portal.favorites.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import se.vgregion.portal.favorites.domain.jpa.Favorite;
import se.vgregion.portal.favorites.exception.CreateFavoriteException;
import se.vgregion.portal.favorites.exception.UpdateFavoriteException;
import se.vgregion.portal.favorites.repository.FavoriteRepository;

public class FavoriteServiceImplTest {
	
	FavoriteServiceImpl favoriteServiceImpl;
	Favorite favorite_new;
	Favorite favorite_1;
	
	long favoriteId_1;
	long companyId;
	long groupId;
	long userId;
	long layoutPlid_1;
	
	@Before
	public void setup() {
	
		FavoriteRepository favoriteRepository = Mockito.mock(FavoriteRepository.class);
		
		favoriteServiceImpl = new FavoriteServiceImpl(favoriteRepository){
		};
		
		favorite_new = new Favorite();
		
		favoriteId_1 = 1111;
		groupId = 2222;
		companyId = 3333;
		userId = 4444;
		layoutPlid_1 = 5555;
		
		favorite_1 = new Favorite();
		favorite_1.setId(favoriteId_1);
		
		Mockito.when(favoriteRepository.find(favoriteId_1)).thenReturn(favorite_1);
	}

	@Test
	public void testAddFavorite() throws CreateFavoriteException {
		
		favoriteServiceImpl.addFavorite(favorite_new);
	}

	@Test
	public void testDeleteFavoriteLong() {
		
		favoriteServiceImpl.deleteFavorite(favoriteId_1);
	}

	@Test
	public void testDeleteFavoriteFavorite() {
		favoriteServiceImpl.deleteFavorite(favorite_1);
	}

	@Test
	public void testFind() {
		favoriteServiceImpl.find(favoriteId_1);
	}

	@Test
	public void testFindFavoritesByCompanyIdLong() {
		favoriteServiceImpl.findFavoritesByCompanyId(companyId);
	}

	@Test
	public void testFindFavoritesByCompanyIdLongIntInt() {
		favoriteServiceImpl.findFavoritesByCompanyId(companyId, 0, 10);
	}

	@Test
	public void testFindFavoritesCountByGroupId() {
		favoriteServiceImpl.findFavoritesByGroupId(companyId, groupId);
	}

	@Test
	public void testFindFavoritesByGroupIdLongLong() {
		favoriteServiceImpl.findFavoritesByGroupId(companyId, groupId);
	}

	@Test
	public void testFindFavoritesByGroupIdLongLongIntInt() {
		favoriteServiceImpl.findFavoritesByGroupId(companyId, groupId, 1, 10);
	}

	@Test
	public void testFindUserFavoritesCount() {
		favoriteServiceImpl.findUserFavorites(companyId, groupId, userId);
	}

	@Test
	public void testFindUserFavoritesLongLongLong() {
		favoriteServiceImpl.findUserFavorites(companyId, groupId, userId);
	}

	@Test
	public void testFindUserFavoritesLongLongLongIntInt() {
		favoriteServiceImpl.findUserFavorites(companyId, groupId, userId, 0, 10);
	}

	@Test
	public void testUpdateFavoriteFavorite() throws UpdateFavoriteException {
		favoriteServiceImpl.updateFavorite(favorite_new);
		
		favoriteServiceImpl.updateFavorite(favorite_1);
	}

	@Test
	public void testUpdateFavoriteLongLong() throws UpdateFavoriteException {
		favoriteServiceImpl.updateFavorite(favoriteId_1, layoutPlid_1);
	}

}
