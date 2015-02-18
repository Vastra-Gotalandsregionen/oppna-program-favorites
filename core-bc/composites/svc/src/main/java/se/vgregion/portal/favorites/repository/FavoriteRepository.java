package se.vgregion.portal.favorites.repository;

import java.util.List;

import se.vgregion.dao.domain.patterns.repository.Repository;
import se.vgregion.portal.favorites.domain.jpa.Favorite;

/**
 * Repository interface for managing {@code Favorite}s.
 *
 * @author Erik Andersson
 * @company Monator Technologies AB
 */
public interface FavoriteRepository extends Repository<Favorite, Long> {

    /**
     * Find the number of {@link Favorite}s for a group in a company.
     *
     * @param companyId the companyid
     * @param groupId   the groupId
     * @return an int with the number of {@link Favorite}s
     */
    int findFavoritesCountByCompanyId(long companyId);
	
	
    /**
     * Find all {@link Favorite}s for a company.
     *
     * @param companyId the companyid
     * @return a {@link List} of {@link Favorite}s
     */
    List<Favorite> findFavoritesByCompanyId(long companyId);
    
    /**
     * Find {@link Favorite}s for a company.
     *
     * @param companyId the companyid
     * @param start   start index
     * @param end   maxIndex
     * @return a {@link List} of {@link Favorite}s
     */
    List<Favorite> findFavoritesByCompanyId(long companyId, int start, int offset);

    /**
     * Find the number of {@link Favorite}s for a group in a company.
     *
     * @param companyId the companyid
     * @param groupId   the groupId
     * @return an int with the number of {@link Favorite}s
     */
    int findFavoritesCountByGroupId(long companyId, long groupId);
    

    /**
     * Find all {@link Favorite}s for a group in a company.
     *
     * @param companyId the companyid
     * @param groupId   the groupId
     * @return a {@link List} of {@link Favorite}s
     */
    List<Favorite> findFavoritesByGroupId(long companyId, long groupId);
    
    /**
     * Find {@link Favorite}s for a group in a company.
     *
     * @param companyId the companyid
     * @param groupId   the groupId
     * @param start   start index
     * @param end   maxIndex
     * @return a {@link List} of {@link Favorite}s
     */
    List<Favorite> findFavoritesByGroupId(long companyId, long groupId, int start, int offset);
    
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
     * Find {@link Favorite}s with a certain favoritePlid for a user in a group in a company.
     *
     * @param companyId the companyid
     * @param groupId   the groupId
     * @param userId   the userId 
     * @param favoritePlid  the plid of the favorite
     * @return a {@link List} of {@link Favorite}s
     */
    List<Favorite> findUserFavoritesByLayoutPlid(long companyId, long groupId, long userId , long layoutPlid);
    
    
    /**
     * Remove the {@link Favorite} with the id
     *
     * @param favoriteId the id of the favorite to remove
     * @return void
     */
    void remove(long favoriteId);

}
