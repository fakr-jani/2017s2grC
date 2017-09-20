package ro.msg.edu.client.filters;

import java.io.IOException;

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

@WebFilter(filterName = "AuthentificationFiler", urlPatterns = { "*.xhtml" })
public class LoginFilter implements Filter {

	@Override
	public void destroy() {
		//We don't need this
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		try {

			HttpServletRequest httpRequest = (HttpServletRequest) request;
			HttpServletResponse httpResponse = (HttpServletResponse) response;
			HttpSession httpSession = httpRequest.getSession(false);

			String requestUrl = httpRequest.getRequestURI();

			boolean isLoginPage = requestUrl.indexOf("/login.xhtml") >= 0;
			boolean isUserLoggedIn = httpSession != null && httpSession.getAttribute("username") != null;
			if (isLoginPage || isUserLoggedIn)
				chain.doFilter(request, response);
			else {
				httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.xhtml");
			}

		} catch (Throwable t) {
			System.out.println(t.getMessage());
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		//We don't need this
	}

}