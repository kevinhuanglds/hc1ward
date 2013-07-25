package org.lds.hcstake.hc1ward.util;

import javax.servlet.http.HttpServletRequest;

import org.lds.hcstake.hc1ward.dal.Permission;
import org.lds.hcstake.hc1ward.dal.PermissionDAO;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class AuthUtil {

	public static boolean hasSignIn() {
		User u = getCurrentUser();
		return (u != null);
	}
	
	public static String createLoginUrl(HttpServletRequest req) {
		UserService uService = UserServiceFactory.getUserService();
		return uService.createLoginURL(req.getRequestURL().toString());
	}
	
	public static User getCurrentUser() {
		UserService uService = UserServiceFactory.getUserService();
		return uService.getCurrentUser();

	}
	
	public static boolean hasAdminRole() {
		boolean result = false ;
		User u = getCurrentUser();
		if (u != null) {
			Permission p = PermissionDAO.getAdminPermission(u.getEmail(),"admin");
			result = (p != null) || (u.getEmail().toLowerCase().equals("kevinhuang.lds@gmail.com"));
		}
		return result ;
	}
}
