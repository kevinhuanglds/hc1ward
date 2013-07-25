package org.lds.hcstake.hc1ward.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lds.hcstake.hc1ward.dal.Permission;
import org.lds.hcstake.hc1ward.dal.PermissionDAO;
import org.lds.hcstake.hc1ward.util.AuthUtil;
import org.lds.hcstake.hc1ward.util.Util;

@SuppressWarnings("serial")
public class PermissionService extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//super.doGet(req, resp);
		
		resp.setContentType("application/json; charset=UTF-8");
		
		String action = req.getRequestURI().replace("/permission", "").replace("/", "");
		if (action.equals("")) action="list";
		if (AuthUtil.hasAdminRole()) {
			
			try {
				String userID = req.getParameter("userID");
				String role = req.getParameter("role");
				
				if (action.equals("delete")) {
					PermissionDAO.deletePermission(userID, role);
					resp.getWriter().write("{'msg' : '¤w¸g§R°£'}");
				}else if (action.equals("add")) {
					Permission p = PermissionDAO.addPermission(userID, role);
					resp.getWriter().write(p.toJSON());
				}else if (action.equals("list")) {
					List<Permission> ps = PermissionDAO.getAll();
					resp.getWriter().write(PermissionDAO.toJSON(ps));
				}
				else {
					Util.sendErrMsg(resp, "unsupported action => " + action);
				}
			}
			catch(Exception ex) {
				Util.sendErrMsg(resp, ex.getMessage());
			}
		}
		else {
			Util.sendErrMsg(resp, "You do not have the administration permission !");
		}
	}

	
}
