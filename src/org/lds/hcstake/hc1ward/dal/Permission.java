package org.lds.hcstake.hc1ward.dal;

import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
/**
 * ∫ﬁ≤z≈v≠≠
 * @author kevin
 *
 */

@PersistenceCapable
public class Permission {
	
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;

	@Persistent
	private String userID;
	
	@Persistent
	private String role;
	
	@Persistent
	private Date createDate ;
	
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Permission(String userID, String role) {
		this.userID = userID;
		this.role = role;
		this.createDate = new Date();
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	public String toJSON() {
		return String.format("{'key' : '%s' , 'userID': '%s', 'role': '%s', 'createTime': '%s'}",
				this.key, this.userID, this.role, this.createDate);
	}
}
