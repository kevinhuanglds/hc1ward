package org.lds.hcstake.hc1ward.util;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

public class Util {
	
	public static void sendErrMsg(HttpServletResponse resp, String msg) throws IOException {
		resp.getWriter().write(String.format("{'errMsg' : '%s'}", msg));
	}

}
