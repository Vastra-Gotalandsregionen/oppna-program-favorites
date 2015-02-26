package se.vgregion.portal.favorites.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import se.vgregion.portal.favorites.domain.jpa.Favorite;
import se.vgregion.portal.favorites.exception.CreateFavoriteException;
import se.vgregion.portal.favorites.exception.UpdateFavoriteException;
import se.vgregion.portal.favorites.repository.FavoriteRepository;

import com.liferay.portal.NoSuchLayoutException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.Layout;
import com.liferay.portal.service.LayoutLocalServiceUtil;

/**
 * Implementation of {@link FavoriteService}.
 *
 * @author Erik Andersson
 * @company Monator Technologies AB
 */
public class FavoriteServiceImpl implements FavoriteService {

	private static final Logger LOGGER = LoggerFactory.getLogger(FavoriteServiceImpl.class.getName());
    private FavoriteRepository favoriteRepository;

    /**
     * Constructor.
     *
     * @param favoriteRepository the {@link FavoriteRepository}
     */
    @Autowired
    public FavoriteServiceImpl(FavoriteRepository favoriteRepository) {
        this.favoriteRepository = favoriteRepository;
    }
    
    @Override
    @Transactional
    public Favorite addFavorite(Favorite favorite) throws CreateFavoriteException {
    	return favoriteRepository.merge(favorite);
    }
    
    @Override
    @Transactional
    public void deleteFavorite(long favoriteId) {
    	Favorite favorite = favoriteRepository.find(favoriteId);
    	
    	if(favorite != null) {
    		favoriteRepository.remove(favoriteId);	
    	}
    }
    
    @Override
    @Transactional
    public void deleteFavorite(Favorite favorite) {
    	favoriteRepository.remove(favorite);
    }

    @Override
    public Favorite find(long favoriteId) {
        return favoriteRepository.find(favoriteId);
    }
    
    @Override
    public List<Favorite> findFavoritesByCompanyId(long companyId) {
        return favoriteRepository.findFavoritesByCompanyId(companyId);
    }
    
    @Override
    public List<Favorite> findFavoritesByCompanyId(long companyId, int start, int offset) {
        return favoriteRepository.findFavoritesByCompanyId(companyId, start, offset);
    }

    @Override
    public int findFavoritesCountByGroupId(long companyId, long groupId) {
        return favoriteRepository.findFavoritesCountByGroupId(companyId, groupId);
    }
    
    
    @Override
    public List<Favorite> findFavoritesByGroupId(long companyId, long groupId) {
        return favoriteRepository.findFavoritesByGroupId(companyId, groupId);
    }
    
    @Override
    public List<Favorite> findFavoritesByGroupId(long companyId, long groupId, int currentPage, int offset) {
    	
    	int start = (currentPage -1) * offset;
    	if(start < 0) {
    		start = 0;
    	}
    	
        return favoriteRepository.findFavoritesByGroupId(companyId, groupId, start, offset);
    }
    
    @Override
    public int findUserFavoritesCount(long companyId, long groupId, long userId) {
    	return favoriteRepository.findUserFavoritesCount(companyId, groupId, userId);
    }
    
    @Override
    public List<Favorite> findUserFavorites(long companyId, long groupId, long userId) {
    	return favoriteRepository.findUserFavorites(companyId, groupId, userId);
    }
    
    @Override
    public List<Favorite> findUserFavorites(long companyId, long groupId, long userId , int start, int offset) {
    	return favoriteRepository.findUserFavorites(companyId, groupId, userId, start, offset);
    }
    
    @Override
    public Favorite findUserFavoriteByPlid(long companyId, long groupId, long userId , long layoutPlid) {
    	Favorite favorite = favoriteRepository.findUserFavoriteByLayoutPlid(companyId, groupId, userId, layoutPlid);
    	
    	return favorite;
    }
    
    @Override
    public List<Layout> getUserFavoriteLayouts(long companyId, long groupId, long userId) {
    	
    	List<Favorite> favorites = findUserFavorites(companyId, groupId, userId);
    	
    	ArrayList<Layout> favoriteLayouts = new ArrayList<Layout>();
    	
    	for(Favorite favorite : favorites) {
        	Layout favoriteLayout = null;
        	
        	try {
    			favoriteLayout = LayoutLocalServiceUtil.getLayout(favorite.getLayoutPlid());
    			
    			favoriteLayouts.add(favoriteLayout);
    		} catch (PortalException e) {
    			
    			if(e instanceof NoSuchLayoutException) {
    				// Do nothing
    			} else {
    				LOGGER.error(e.getMessage(), e);	
    			}
    		} catch (SystemException e) {
    			LOGGER.error(e.getMessage(), e);
    		}
    	}
    	
    	
    	return favoriteLayouts;
    }
    
    @Override
    public Boolean isLayoutUserFavorite(long companyId, long groupId, long userId, long layoutPlid) {
    	
    	Favorite favorite = favoriteRepository.findUserFavoriteByLayoutPlid(companyId, groupId, userId, layoutPlid);
    	
    	return (favorite != null);
    }
    
    @Override
    @Transactional
    public Favorite updateFavorite(Favorite favorite) throws UpdateFavoriteException {
    	return favoriteRepository.merge(favorite);
    }

    @Override
    @Transactional
    public Favorite updateFavorite(long favoriteId, long layoutPlid) throws UpdateFavoriteException {
    	
    	Favorite favorite = favoriteRepository.find(layoutPlid);
    	
    	if(favorite != null) {
    		favorite.setLayoutPlid(layoutPlid);
    	
    		favorite = favoriteRepository.merge(favorite);
    	}
    	
    	return favorite;
    }

}
