package com.saas.sales_setup.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception for cases where a resource is not found in the database.
 * The @ResponseStatus annotation tells Spring to automatically return a 404 NOT FOUND
 * HTTP status whenever this exception is thrown from a controller.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}