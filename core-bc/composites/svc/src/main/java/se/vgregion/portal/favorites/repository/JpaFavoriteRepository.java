package se.vgregion.portal.favorites.repository;

import se.vgregion.dao.domain.patterns.repository.db.jpa.JpaRepository;
import se.vgregion.portal.favorites.domain.jpa.Favorite;

/**
 * JPA Repository interface for managing {@link Favorite}s.
 *
 * @author Erik Andersson
 * @company Monator Technologies AB
 */
public interface JpaFavoriteRepository extends JpaRepository<Favorite, Long, Long>,
        FavoriteRepository {

}
