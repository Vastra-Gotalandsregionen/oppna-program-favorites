package se.vgregion.portal.favorites.service;

import java.util.List;

import com.liferay.portal.model.Layout;

import se.vgregion.portal.favorites.domain.jpa.Favorite;
import se.vgregion.portal.favorites.exception.CreateFavoriteException;
import se.vgregion.portal.favorites.exception.UpdateFavoriteException;

/**
 * Service interface for managing {@link Favorite}s.
 *
 * @author Erik Andersson
 * @company Monator Technologies AB
 */
public interface FavoriteService {

    /**
     * Add a {@link Favorite}.
     *
     */
    Favorite addFavorite(Favorite favorite) throws CreateFavoriteException;
    
    /**
     * Remove a {@link Favorite}.
     *
     * @param favoriteId the primaryKey (id) of the {@link Favorite} to remove
     */
    void deleteFavorite(long favoriteId);
    
    
    /**
     * Remove a {@link Favorite}.
     *
     * @param favorite the {@link Favorite} to remove
     */
    void deleteFavorite(Favorite favorite);
    
    /**
     * Find a {@link Favorite}.
     *
     * @param favoriteId - the primaryKey of the favorite
     *
     * @return the {@link Favorite}.
     */
    Favorite find(long favoriteId);    
	
    /**
     * Find all {@link Favorite} for a given company.
     *
     * @param companyId the companyId
     * @return all {@link Favorite} for the given company
     */
    List<Favorite> findFavoritesByCompanyId(long companyId);
    
    /**
     * Find {@link Favorite} for a given company.
     *
     * @param companyId the companyId
     * @param start   start index
     * @param end   maxIndex 
     * @return all {@link Favorite} for the given company
     */
    List<Favorite> findFavoritesByCompanyId(long companyId, int start, int offset);

    /**
     * Find the number of {@link Favorite}s for a group in a company.
     *
     * @param companyId the companyId
     * @return an int with the number of {@link Favorite}s
     */
    int findFavoritesCountByGroupId(long companyId, long groupId);
    

    /**
     * Find all {@link Favorite} for a given company and group.
     *
     * @param companyId the companyId
     * @return all {@link Favorite} for the given company and group
     */
    List<Favorite> findFavoritesByGroupId(long companyId, long groupId);
    
    /**
     * Find all {@link Favorite} for a given company and group.
     *
     * @param companyId the companyId
     * @param start   start index
     * @param end   maxIndex 
 	 *
     * @return all {@link Favorite} for the given company and group
     */
    List<Favorite> findFavoritesByGroupId(long companyId, long groupId, int currentPage, int offset);
    
    /**
     * Find the number of {@link Favorite}s for a user in a group in a company.
     *
     * @param companyId the companyid
     * @param groupId   the groupId
     * @param userId   the userId 
     * @return an int with the number of {@link Favorite}s
     */
    int findUserFavoritesCount(long companyId, long groupId, long userId);
    

    /**
     * Find all {@link Favorite}s for a user in a group in a company.
     *
     * @param companyId the companyid
     * @param groupId   the groupId
     * @param userId   the userId 
     * @return a {@link List} of {@link Favorite}s
     */
    List<Favorite> findUserFavorites(long companyId, long groupId, long userId);
    
    /**
     * Find {@link Favorite}s with a layoutPlid for a user in a group in a company.
     *
     * @param companyId the companyid
     * @param groupId   the groupId
     * @param userId   the userId 
     * @param layoutPlid   the layoutPlid
     * @return a {@link List} of {@link Favorite}s
     */
    List<Favorite> findUserFavoritesByPlid(long companyId, long groupId, long userId , long layoutPlid);
    
    /**
     * Find {@link Favorite}s for a user in a group in a company.
     *
     * @param companyId the companyid
     * @param groupId   the groupId
     * @param userId   the userId 
     * @param start   start index
     * @param end   maxIndex
     * @return a {@link List} of {@link Favorite}s
     */
    List<Favorite> findUserFavorites(long companyId, long groupId, long userId , int start, int offset);
    
    /**
     * Find {@link Layout}s for a user in a group in a company.
     *
     * @param companyId the companyid
     * @param groupId   the groupId
     * @param userId   the userId 
     * @param start   start index
     * @param end   maxIndex
     * @return a {@link List} of {@link Favorite}s
     */
    List<Layout> getUserFavoriteLayouts(long companyId, long groupId, long userId);
    

    /**
     * Check whether a layout of a certain plid has been added as a {@link Favorite}s for a user in a group in a company.
     *
     * @param companyId the companyid
     * @param groupId   the groupId
     * @param userId   the userId 
     * @param layoutPlid   the layoutPlid 
     * @return a {@link Boolean}
     */
    Boolean isLayoutUserFavorite(long companyId, long groupId, long userId, long layoutPlid);
    
    /**
     * Update a {@link Favorite}.
     *
     */
    Favorite updateFavorite(Favorite favorite) throws UpdateFavoriteException;
    
    /**
     * Update a {@link Favorite}.
     *
     */
    Favorite updateFavorite(long favoriteId, long layoutPlid) throws UpdateFavoriteException;
    
    
}
