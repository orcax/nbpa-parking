package edu.ru.cee.nbpap.servlet.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class SessionInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp,
			Object arg2) throws Exception {
		String token = (String) req.getSession().getAttribute("token");
		String projectName = req.getContextPath().toString();
		if (req.getRequestURI().contains("login.html")) {
			if (token != null) {
				resp.sendRedirect(projectName + "/index.html");
				return false;
			} else {
				return true;
			}
		}
		if (token == null) {
			if (req.getRequestURI().contains("api/occupancy")) {
				resp.sendError(HttpServletResponse.SC_REQUEST_TIMEOUT);
			} else {
				resp.sendRedirect(projectName + "/login.html");
			}
			return false;
		}
		return true;
	}

}
