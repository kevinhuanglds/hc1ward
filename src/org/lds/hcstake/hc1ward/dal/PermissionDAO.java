package org.lds.hcstake.hc1ward.dal;

import java.util.List;
import java.util.Map;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.google.appengine.api.datastore.Entity;



public class PermissionDAO {
	
	private static PersistenceManager pm = PMF.get().getPersistenceManager();

	public static Permission addPermission(String userID, String role) {
		Permission result = getAdminPermission(userID, role) ;
		//1. 判斷是否已經存在
		if (result == null) {
			result = new Permission(userID, role);
			try {
				pm.makePersistent(result);
			}
			finally {
				//pm.close();
			}
		}		
		return result ;
	}
	
	public static Permission getAdminPermission(String userID, String role) {
		Permission p = null;
		
		Query q = pm.newQuery(Permission.class);
		q.setFilter("role == 'admin'");
		q.setFilter("userID == UserID");
		q.declareParameters("String UserID");
		
		List<Permission> results = (List<Permission>) q.execute(userID);
		if (!results.isEmpty()) {
			p = results.get(0);
		}
		
		return p;
	}
	
	public static void deletePermission(String userID, String role) {
		Permission p = getAdminPermission(userID, role);
		if (p != null)
		{
			pm.deletePersistent(p);
		}
	}
	
	public static List<Permission> getAll() {
		Query q = pm.newQuery(Permission.class);
		return (List<Permission>) q.execute();
	}
	
	public static String toJSON(List<Permission> ps) {
		StringBuilder sb = new StringBuilder();
		
		int i = 0;
	  	sb.append("[");
	  	for (Permission p : ps) {
	  	  sb.append(p.toJSON());
	  	  sb.append(",");
	  	  i++;
	  	}
	  	if (i > 0)
	  	  sb.deleteCharAt(sb.lastIndexOf(","));
	  	  
	  	sb.append("]");
	  	return sb.toString();
	}
}
