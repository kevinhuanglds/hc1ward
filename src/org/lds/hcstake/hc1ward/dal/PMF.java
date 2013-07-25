package org.lds.hcstake.hc1ward.dal;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

public class PMF {
	
	private static final PersistenceManagerFactory factory=JDOHelper.getPersistenceManagerFactory("transactions-optional");
	
	public static PersistenceManagerFactory get() {
				
		return factory;
	}

}
