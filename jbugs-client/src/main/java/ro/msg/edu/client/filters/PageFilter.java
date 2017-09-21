package ro.msg.edu.client.filters;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ro.msg.edu.client.beans.PermissionsCheck;
import ro.msg.edu.persistence.user.entity.enums.PermissionType;

@WebFilter(filterName = "PermissionFilter", urlPatterns = { "*.xhtml" })
public class PageFilter implements Filter {
	public final static String USERNAME = "username";
	public final static String BUGS_PAGE = "/bugs.xhtml";
	public final static String ADDBUGS_PAGE = "/addBug.xhtml";
	public final static String EDITBUGS_PAGE = "/editBugs.xhtml";
	public final static String EDITBUGSTATUS_PAGE = "/editBugStatus.xhtml";
	public final static String CLOSEBUG_PAGE = "/closeBug.xhtml";
	public final static String ADDPERMISSION_PAGE = "/addPermission.xhtml";
	public final static String REMOVEPERMISSION_PAGE = "/removePermission.xhtml";
	public final static String ADDUSER_PAGE = "/addUser.xhtml";
	public final static String UPDATEUSER_PAGE = "/editUsers.xhtml";
	public final static String DELETEUSER_PAGE = "/deleteUser.xhtml";
	public final static String LOGIN_PAGE = "/login.xhtml";
	public final static String RAPORTBUG_PAGE = "/raportStatusBug.xhtml";
	public final static String RAPORTUSERS_PAGE = "/raportUsers.xhtml";

	@EJB
	private PermissionsCheck permissionCheck;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// We don't need this

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		try {
			String username = "";
			HttpServletRequest httpRequest = (HttpServletRequest) request;
			HttpServletResponse httpResponse = (HttpServletResponse) response;
			HttpSession httpSession = httpRequest.getSession(false);
			String requestUrl = httpRequest.getRequestURI();
			if (httpSession != null && httpSession.getAttribute(USERNAME) != null) {
				username = httpSession.getAttribute(USERNAME).toString();
			} else {
				username = null;
			}
			boolean isUserLoggedIn = httpSession != null && httpSession.getAttribute(USERNAME) != null;
			if (!(requestUrl.indexOf(LOGIN_PAGE) >= 0) && !isUserLoggedIn) {
				httpResponse.sendRedirect(httpRequest.getContextPath() + LOGIN_PAGE);
			}
			if ((requestUrl.indexOf(ADDBUGS_PAGE) >= 0 || requestUrl.indexOf(EDITBUGS_PAGE) >= 0)
					&& !permissionCheck.verifyPermissionRendered(username, PermissionType.BUG_MANAGEMENT.toString())) {
				httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
			}
			if (requestUrl.indexOf(BUGS_PAGE) >= 0 && (!permissionCheck.verifyPermissionRendered(username,
					PermissionType.BUG_MANAGEMENT.toString())
					&& !permissionCheck.verifyPermissionRendered(username, PermissionType.BUG_EXPORT_PDF.toString()))) {
				httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
			}
			if ((requestUrl.indexOf(RAPORTBUG_PAGE) >= 0 || requestUrl.indexOf(RAPORTUSERS_PAGE) >= 0)
					&& !permissionCheck.verifyPermissionRendered(username, PermissionType.BUG_MANAGEMENT.toString())) {
				httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
			}
			if (requestUrl.indexOf(EDITBUGSTATUS_PAGE) >= 0 && (!permissionCheck.verifyPermissionRendered(username,
					PermissionType.BUG_MANAGEMENT.toString())
					&& !permissionCheck.verifyPermissionRendered(username, PermissionType.BUG_CLOSE.toString()))) {
				httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
			}
			if (requestUrl.indexOf(CLOSEBUG_PAGE) >= 0
					&& !permissionCheck.verifyPermissionRendered(username, PermissionType.BUG_CLOSE.toString())) {
				httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
			}
			if ((requestUrl.indexOf(ADDPERMISSION_PAGE) >= 0 || requestUrl.indexOf(REMOVEPERMISSION_PAGE) >= 0)
					&& !permissionCheck.verifyPermissionRendered(username,
							PermissionType.PERMISSION_MANAGEMENT.toString())) {
				httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
			}
			if ((requestUrl.indexOf(ADDUSER_PAGE) >= 0 || requestUrl.indexOf(EDITBUGS_PAGE) >= 0
					|| requestUrl.indexOf(DELETEUSER_PAGE) >= 0)
					&& !permissionCheck.verifyPermissionRendered(username, PermissionType.USER_MANAGEMENT.toString())) {
				httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
			}

			else {
				chain.doFilter(request, response);
			}

		} catch (Throwable t) {
			System.out.println(t.getMessage());
		}

	}

	@Override
	public void destroy() {
		// We don't need this

	}

}
