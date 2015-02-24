package se.vgregion.portal.favorites.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import se.vgregion.dao.domain.patterns.repository.db.jpa.DefaultJpaRepository;
import se.vgregion.portal.favorites.domain.jpa.Favorite;

/**
 * Implementation of {@link JpaFavoriteRepository}.
 *
 * @author Erik Andersson
 * @company Monator Technologies AB
 */
public class JpaFavoriteRepositoryImpl extends DefaultJpaRepository<Favorite, Long>
        implements JpaFavoriteRepository {
	
    @Override
    public int findFavoritesCountByCompanyId(long companyId) {
        
        String queryString = "" 
        		+ " SELECT COUNT(DISTINCT n) FROM Favorite n" 
        		+ " WHERE n.companyId = ?1";
        
        Object[] queryObject = new Object[]{companyId};

        int count = findCountByQuery(queryString, queryObject);
        
        return count;
    }
	

    @Override
    public List<Favorite> findFavoritesByCompanyId(long companyId) {
        
        String queryString = "" 
        		+ " SELECT DISTINCT n FROM Favorite n" 
        		+ " WHERE n.companyId = ?1" 
        		+ " ORDER BY n.id ASC";
        
    	Object[] queryObject = new Object[]{companyId};

        List<Favorite> favorites = findByQuery(queryString, queryObject);

        return favorites;
    }
    
    @Override
    public List<Favorite> findFavoritesByCompanyId(long companyId, int start, int offset) {
        
        String queryString = "" 
        		+ " SELECT DISTINCT n FROM Favorite n" 
        		+ " WHERE n.companyId = ?1" 
        		+ " ORDER BY n.id ASC";
        
    	Object[] queryObject = new Object[]{companyId};

        List<Favorite> favorites = findByPagedQuery(queryString, queryObject, start, offset);

        return favorites;
    }
    
    @Override
    public int findFavoritesCountByGroupId(long companyId, long groupId) {
        
        String queryString = "" 
        		+ " SELECT COUNT(DISTINCT n) FROM Favorite n" 
        		+ " WHERE n.companyId = ?1" 
        		+ " AND n.groupId = ?2";
        
        Object[] queryObject = new Object[]{companyId, groupId};

        int count = findCountByQuery(queryString, queryObject);
        
        return count;
    }
    

    @Override
    public List<Favorite> findFavoritesByGroupId(long companyId, long groupId) {
        
        String queryString = "" 
        		+ " SELECT DISTINCT n FROM Favorite n" 
        		+ " WHERE n.companyId = ?1" 
        		+ " AND n.groupId = ?2" 
        		+ " ORDER BY n.id ASC";
        

        Object[] queryObject = new Object[]{companyId, groupId};
        
        List<Favorite> favorites = findByQuery(queryString, queryObject);
        
        return favorites;
    }
    
    @Override
    public List<Favorite> findFavoritesByGroupId(long companyId, long groupId, int start, int offset) {
        
        String queryString = "" 
        		+ " SELECT DISTINCT n FROM Favorite n" 
        		+ " WHERE n.companyId = ?1" 
        		+ " AND n.groupId = ?2" 
        		+ " ORDER BY n.id ASC";
        

        Object[] queryObject = new Object[]{companyId, groupId};
        
        List favorites = findByPagedQuery(queryString, queryObject, start, offset);
        
        return favorites;
    }
    
    
    
    
    
    @Override
    public int findUserFavoritesCount(long companyId, long groupId, long userId) {
        
        String queryString = "" 
        		+ " SELECT COUNT(DISTINCT n) FROM Favorite n" 
        		+ " WHERE n.companyId = ?1" 
        		+ " AND n.groupId = ?2"
        		+ " AND n.userId = ?3";
        
        Object[] queryObject = new Object[]{companyId, groupId, userId};

        int count = findCountByQuery(queryString, queryObject);
        
        return count;
    }
    

    @Override
    public List<Favorite> findUserFavorites(long companyId, long groupId, long userId) {
        
        String queryString = "" 
        		+ " SELECT DISTINCT n FROM Favorite n" 
        		+ " WHERE n.companyId = ?1" 
        		+ " AND n.groupId = ?2"
        		+ " AND n.userId = ?3"
        		+ " ORDER BY n.id ASC";
        

        Object[] queryObject = new Object[]{companyId, groupId, userId};
        
        List favorites = findByQuery(queryString, queryObject);
        
        return favorites;
    }
    
    @Override
    public List<Favorite> findUserFavorites(long companyId, long groupId, long userId, int start, int offset) {
        
        String queryString = "" 
        		+ " SELECT DISTINCT n FROM Favorite n" 
        		+ " WHERE n.companyId = ?1" 
        		+ " AND n.groupId = ?2"
        		+ " AND n.userId = ?3"
        		+ " ORDER BY n.id ASC";
        

        Object[] queryObject = new Object[]{companyId, groupId, userId};
        
        List favorites = findByPagedQuery(queryString, queryObject, start, offset);
        
        return favorites;
    }
    
    @Override
    public Favorite findUserFavoriteByLayoutPlid(long companyId, long groupId, long userId , long layoutPlid) {
    	
        String queryString = "" 
        		+ " SELECT DISTINCT n FROM Favorite n" 
        		+ " WHERE n.companyId = ?1" 
        		+ " AND n.groupId = ?2"
        		+ " AND n.userId = ?3"
        		+ " AND n.layoutPlid = ?4"
        		+ " ORDER BY n.id ASC";
    	
        Object[] queryObject = new Object[]{companyId, groupId, userId, layoutPlid};
        
        List favorites = findByQuery(queryString, queryObject);
        
        if(favorites.size() > 0) {
        	return (Favorite)favorites.get(0);
        } else {
        	return null;
        }
    }
    
    @Override
    public void remove(long favoriteId) {
        
        Favorite favorite = find(favoriteId);
        
        remove(favorite);
    }

    private List<Favorite> findByPagedQuery(String queryString, Object[] queryObject, int firstResult, int maxResult) {

    	Query query = createQuery(queryString, queryObject);
        
        query.setFirstResult(firstResult);
        query.setMaxResults(maxResult);
        
        return query.getResultList();
    }
    
    private int findCountByQuery(String queryString, Object[] queryObject) {
        Query query = createQuery(queryString, queryObject);
        
        Number result = (Number) query.getSingleResult();
        
        return result.intValue();
    }
    
    private Query createQuery(String queryString, Object[] queryObject) {
    	
        Query query = entityManager.createQuery(queryString);
        if (queryObject != null) {
            for (int i = 0; i < queryObject.length; i++) {
                query.setParameter(i + 1, queryObject[i]);
            }
        }
        
        return query;
    }
    
    
}
