package se.vgregion.portal.favorites.repository;

import java.lang.reflect.Field;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import se.vgregion.dao.domain.patterns.repository.db.jpa.AbstractJpaRepository;

public class JpaFavoriteRepositoryImplTest {
	
	long favoriteId_1;
	long companyId_1;
	long groupId_1;
	long userId_1;
	
	EntityManager entityManager;
	JpaFavoriteRepositoryImpl jpaFavoriteRepositoryImpl;
	Query query;
	
	@Before
	public void setup() throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
		
		favoriteId_1 = 3333;
		companyId_1 = 1234;
		groupId_1 = 5678;
		userId_1 = 2222;
		
		entityManager = Mockito.mock(EntityManager.class);	
		jpaFavoriteRepositoryImpl = new JpaFavoriteRepositoryImpl();
		
		query = Mockito.mock(Query.class);
		
		Mockito.when(query.getSingleResult()).thenReturn(0);
		
		Field entityManagerField = AbstractJpaRepository.class.getDeclaredField("entityManager");
		entityManagerField.setAccessible(true);
		
		entityManagerField.set(jpaFavoriteRepositoryImpl, entityManager);
		
		Mockito.when(entityManager.createQuery(Mockito.anyString())).thenReturn(query);
	}

	@Test
	public void testFindFavoritesCountByCompanyId() {
		jpaFavoriteRepositoryImpl.findFavoritesCountByCompanyId(companyId_1);
	}

	@Test
	public void testFindFavoritesByCompanyIdLong() {
		jpaFavoriteRepositoryImpl.findFavoritesByCompanyId(companyId_1);
	}

	@Test
	public void testFindFavoritesByCompanyIdLongIntInt() {
		jpaFavoriteRepositoryImpl.findFavoritesByCompanyId(companyId_1, 0, 10);
	}

	@Test
	public void testFindFavoritesCountByGroupId() {
		jpaFavoriteRepositoryImpl.findFavoritesCountByGroupId(companyId_1, groupId_1);
	}

	@Test
	public void testFindFavoritesByGroupIdLongLong() {
		jpaFavoriteRepositoryImpl.findFavoritesByGroupId(companyId_1, groupId_1);
	}

	@Test
	public void testFindFavoritesByGroupIdLongLongIntInt() {
		jpaFavoriteRepositoryImpl.findFavoritesByGroupId(companyId_1, groupId_1, 0, 10);
	}

	@Test
	public void testFindUserFavoritesCount() {
		jpaFavoriteRepositoryImpl.findUserFavoritesCount(companyId_1, groupId_1, userId_1);
	}

	@Test
	public void testFindUserFavoritesLongLongLong() {
		jpaFavoriteRepositoryImpl.findUserFavorites(companyId_1, groupId_1, userId_1);
	}

	@Test
	public void testFindUserFavoritesLongLongLongIntInt() {
		jpaFavoriteRepositoryImpl.findUserFavorites(companyId_1, groupId_1, userId_1, 0, 10);
	}

	@Test
	public void testRemoveLong() {
		jpaFavoriteRepositoryImpl.remove(favoriteId_1);
	}

	
}
