package com.saas.sales_setup.config;

import com.saas.sales_setup.interceptor.TenantInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * This class is used to configure Spring MVC.
 * Here, we register our custom TenantInterceptor.
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private TenantInterceptor tenantInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // This tells Spring to apply our TenantInterceptor to all incoming requests.
        registry.addInterceptor(tenantInterceptor);
    }
}