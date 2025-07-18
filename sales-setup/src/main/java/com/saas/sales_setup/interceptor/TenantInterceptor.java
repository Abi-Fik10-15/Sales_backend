package com.saas.sales_setup.interceptor;

import com.saas.sales_setup.config.TenantContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * This interceptor is executed for every incoming request. Its purpose is to
 * extract the tenant ID from the JWT token and set it in the TenantContext.
 */
@Component
public class TenantInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // This method is called before the controller method is invoked.
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        // Check if the user is authenticated and the principal is a JWT.
        if (authentication != null && authentication.getPrincipal() instanceof Jwt) {
            Jwt principal = (Jwt) authentication.getPrincipal();

            // The 'iss' (issuer) claim in the JWT contains the URL of the Keycloak realm.
            // Example: "http://localhost:9090/realms/my-tenant-realm"
            String issuer = principal.getIssuer().toString();

            // We extract the last part of the URL, which is the realm name.
            String tenantId = issuer.substring(issuer.lastIndexOf('/') + 1);

            // Set the extracted tenantId in our ThreadLocal context.
            TenantContext.setCurrentTenant(tenantId);
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // This method is called after the request is completed.
        // We clear the TenantContext to prevent memory leaks.
        TenantContext.clear();
    }
}
