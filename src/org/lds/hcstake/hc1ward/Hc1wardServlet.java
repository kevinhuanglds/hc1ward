package org.lds.hcstake.hc1ward;
import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lds.hcstake.hc1ward.util.AuthUtil;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

@SuppressWarnings("serial")
public class Hc1wardServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		UserService uService = UserServiceFactory.getUserService();
		User user = uService.getCurrentUser();
		
		resp.setContentType("text/html; charset=UTF-8");
		
		if (user != null) {
			resp.getWriter().println(String.format("Hello, %s", user.getNickname()));
		}
		else {
			String msg = String.format("You have to <a href='%s'>Sign In</a>", 
					uService.createLoginURL(req.getRequestURI()));
			resp.getWriter().println(msg);
		}
	}
}
