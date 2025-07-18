package com.saas.sales_setup.config;

/**
 * This class holds the tenant ID for the current request.
 * It uses a ThreadLocal, which means each thread (and thus each request)
 * will have its own separate copy of the tenant ID. This is a standard
 * pattern for safely handling per-request data in a multi-threaded environment.
 */
public class TenantContext {

    private static final ThreadLocal<String> currentTenant = new ThreadLocal<>();

    public static String getCurrentTenant() {
        return currentTenant.get();
    }

    public static void setCurrentTenant(String tenant) {
        currentTenant.set(tenant);
    }

    public static void clear() {
        // It's important to clear the ThreadLocal after the request is complete
        // to prevent memory leaks in application server environments.
        currentTenant.remove();
    }
}