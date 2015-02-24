package se.vgregion.portal.favorites.domain.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import se.vgregion.dao.domain.patterns.entity.AbstractEntity;

/**
 * JPA entity class representing a Favorite
 *
 * @author Erik Andersson
 * @company Monator Technologies AB
 */
@Entity
@Table(
	name = "vgr_favorite",
	uniqueConstraints  = @UniqueConstraint(columnNames = {"company_id", "group_id", "user_id", "layout_plid"})
)
public class Favorite extends AbstractEntity<Long> {

	// Primary Key
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	// Liferay Related
	
    @Column(name = "company_id")
    private long companyId;
	
    @Column(name = "group_id")
    private long groupId;

    @Column(name = "user_id")
    private long userId;
    
    @Column(name = "layout_plid")
    private long layoutPlid;
    
    /**
     * Constructor.
     */
    public Favorite() {
    }
    
    public Favorite(long companyId, long groupId, long userId) {
    	this.companyId = companyId;
    	this.groupId = groupId;
    	this.userId = userId;
    }
    
    public Favorite(long companyId, long groupId, long userId, long layoutPlid) {
    	this.companyId = companyId;
    	this.groupId = groupId;
    	this.userId = userId;
    	this.layoutPlid = layoutPlid;
    }
    

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	public long getGroupId() {
		return groupId;
	}

	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getLayoutPlid() {
		return layoutPlid;
	}

	public void setLayoutPlid(long layoutPlid) {
		this.layoutPlid = layoutPlid;
	}
    
}
