package com.online.shopping.hub.category.exception;

/**
 * Exception thrown when there is a database error related to categories.
 */
public class CategoryDatabaseException extends RuntimeException {
    /**
     * This exception is thrown when a database operation related to categories fails.
     *
     * @param message the detail message explaining the error
     * @param cause the cause of the exception
     */
    public CategoryDatabaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
