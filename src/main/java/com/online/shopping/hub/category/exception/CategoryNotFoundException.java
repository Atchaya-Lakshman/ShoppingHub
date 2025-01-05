package com.online.shopping.hub.category.exception;

/**
 * Exception thrown when a requested category is not found in the database.
 */
public class CategoryNotFoundException extends RuntimeException {
    /**
     * This exception is thrown when a specific category is not found in the database.
     *
     * @param message A message describing the exception.
     * @param cause The underlying cause of the exception.
     */
    public CategoryNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor for CategoryNotFoundException that takes a custom message.
     *
     * @param message the detail message to describe the cause of the exception
     */
    public CategoryNotFoundException(String message) {
        super(message);
    }
}
