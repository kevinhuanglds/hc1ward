package org.lds.hcstake.hc1ward.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lds.hcstake.hc1ward.dal.MemberDAO;
import org.lds.hcstake.hc1ward.dal.MemberInfo;
import org.lds.hcstake.hc1ward.util.AuthUtil;
import org.lds.hcstake.hc1ward.util.Util;

@SuppressWarnings("serial")
public class MemberService extends HttpServlet {

	/**
	 * enable, disable, delete, list, get
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		resp.setContentType("application/json; charset=UTF-8");

		String action = req.getRequestURI().replace("/member", "")
				.replace("/", "");
		
		if (action.equals("")) action="index";

		if (action.equalsIgnoreCase("index")) {
			 req.getRequestDispatcher("/member.jsp").forward(req, resp);
		}
		else if (action.toLowerCase().equals("list")) {
			resp.getWriter().write(MemberDAO.toJSON(MemberDAO.getAll()));
		} else // get , enable, disable, delete
		{
			String recNo = req.getParameter("recNo");
			if (recNo != null) {
				MemberInfo mi = MemberDAO.get(recNo);
				if (action.toLowerCase().equals("get"))
					resp.getWriter().write(mi.toJSON());
				else {
					if (!AuthUtil.hasAdminRole()) {
						resp.getWriter().write(String.format("{'errMsg': '%s'}", "Insufficient Permission !"));
						return ;
					}
					if (action.toLowerCase().equals("enable")) {
						mi.setActive(true);
						MemberDAO.set(mi);
						resp.getWriter().write(mi.toJSON());
					} else if (action.toLowerCase().equals("disable")) {
						mi.setActive(false);
						MemberDAO.set(mi);
						resp.getWriter().write(mi.toJSON());
					} else if (action.equalsIgnoreCase("delete")) {
						MemberDAO.delete(mi);
						resp.getWriter()
								.write("{msg : 'Delete successfully.'}");
					}
				}

			} else {
				Util.sendErrMsg(resp, "Please specify the chruch record.");
			}
		}
	}

	/**
	 * Insert or Update Member info.
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// super.doPost(req, resp);

		resp.setContentType("application/json; charset=UTF-8");

		String action = req.getRequestURI().replace("/permission", "")
				.replace("/", "");

		if (action.toLowerCase().equals("set")) {
			String recNo = req.getParameter("recNo");
			String name = req.getParameter("name");
			if ((recNo != null) && (name != null)) {
				MemberInfo m = MemberDAO.get(recNo);
				if (m == null) {
					m = new MemberInfo(recNo, name);
				}
				if (req.getParameter("gender") != null)
					m.setGender(req.getParameter("gender"));

				if (req.getParameter("age") != null)
					m.setAge(Integer.parseInt(req.getParameter("age")));

				if (req.getParameter("birthday") != null)
					m.setBirthday(req.getParameter("birthday"));

				if (req.getParameter("home_phone") != null)
					m.setHome_phone(req.getParameter("home_phone"));

				if (req.getParameter("cell_phone") != null)
					m.setCell_phone(req.getParameter("cell_phone"));

				if (req.getParameter("address") != null)
					m.setAddress(req.getParameter("address"));

				if (req.getParameter("pristhood") != null)
					m.setPristhood(req.getParameter("pristhoo"));

				MemberDAO.set(m);
				resp.getWriter().write(m.toJSON());
			} else {
				Util.sendErrMsg(resp,
						"Please specify the chruch record and name.");
			}
		} else {
			Util.sendErrMsg(resp, "action :" + action
					+ " do not support POST method.");
		}

	}

}
