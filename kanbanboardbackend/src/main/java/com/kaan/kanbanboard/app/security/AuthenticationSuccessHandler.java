package com.kaan.kanbanboard.app.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.stereotype.Component;

/**
 * @author Kaan Kocabas
 *
 */
@Component
public class AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	private RequestCache requestCache = new HttpSessionRequestCache();

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		response.setStatus(HttpServletResponse.SC_OK);
		/**
		 * Shows the details of the current user on successful logins
		 */
		System.out.println(SecurityUtils.getCurrentUserLogin().get());
		System.out.println(SecurityUtils.getUsername() + " has logged in");
		System.out.println(SecurityUtils.getUserRoles());
		System.out.println(SecurityUtils.isAuthenticated());
	}

//	@Override
//    public void onAuthenticationSuccess(
//      HttpServletRequest request,
//      HttpServletResponse response, 
//      Authentication authentication) 
//      throws ServletException, IOException {
//  
//        SavedRequest savedRequest
//          = requestCache.getRequest(request, response);
// 
//        if (savedRequest == null) {
//            clearAuthenticationAttributes(request);
//            return;
//        }
//        String targetUrlParam = getTargetUrlParameter();
//        if (isAlwaysUseDefaultTargetUrl()
//          || (targetUrlParam != null
//          && StringUtils.hasText(request.getParameter(targetUrlParam)))) {
//            requestCache.removeRequest(request, response);
//            clearAuthenticationAttributes(request);
//            return;
//        }
// 
//        clearAuthenticationAttributes(request);
//    }

	public void setRequestCache(RequestCache requestCache) {
		this.requestCache = requestCache;
	}

}
