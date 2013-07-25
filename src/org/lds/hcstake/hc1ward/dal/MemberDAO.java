package org.lds.hcstake.hc1ward.dal;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class MemberDAO {
	private static PersistenceManager pm = PMF.get().getPersistenceManager();

	public static MemberInfo get(String recNo) {
		MemberInfo m = null;

		Query q = pm.newQuery(MemberInfo.class);
		q.setFilter("recNo == recNo");
		q.declareParameters("String recNo");

		List<MemberInfo> results = (List<MemberInfo>) q.execute(recNo);
		if (!results.isEmpty()) {
			m = results.get(0);
		}

		return m;
	}
	
	public static MemberInfo set(MemberInfo member) {
		try {
			pm.makePersistent(member);
		}
		finally {
			//pm.close();
		}
		return member;
	}
	
	public static List<MemberInfo> getAll() {
		Query q = pm.newQuery(MemberInfo.class);
		return (List<MemberInfo>) q.execute();
	}
	
	public static void delete(MemberInfo mi) {
		pm.deletePersistent(mi);
	}
	

	public static String toJSON(List<MemberInfo> ps) {
		StringBuilder sb = new StringBuilder();
		
		int i = 0;
	  	sb.append("[");
	  	for (MemberInfo m : ps) {
	  	  sb.append(m.toJSON());
	  	  sb.append(",");
	  	  i++;
	  	}
	  	if (i > 0)
	  	  sb.deleteCharAt(sb.lastIndexOf(","));
	  	  
	  	sb.append("]");
	  	return sb.toString();
	}

}
