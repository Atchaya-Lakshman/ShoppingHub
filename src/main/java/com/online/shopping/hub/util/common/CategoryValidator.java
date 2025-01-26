package com.online.shopping.hub.util.common;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Validates category name, description, and image URL for an online shopping hub.
 * Ensures category name has valid characters, description is not blank, and image URL is valid.
 */
public class CategoryValidator {

    // Method to validate category name, description, and image URL for category create
    public static ArrayList<String> validateCategoryForCreate(String categoryName, String description, String imageUrl) {
        // Initialize the list to store validation error messages
        ArrayList<String> errorList = new ArrayList<>();

        // Validate category name
        if (Objects.isNull(categoryName) || categoryName.isBlank()) {
            // Check if the category name is null or blank (includes empty strings and strings with only whitespace).
            errorList.add("Please enter a category name.");
        } else {
            // Trim the category name to remove leading and trailing whitespace.
            String trimmedCategoryName = categoryName.trim();
            if (!trimmedCategoryName.matches("^[a-zA-Z ]*$")) {
                // Check if the trimmed category name contains only valid characters (letters and spaces).
                errorList.add("Please enter a valid category name (only uppercase and lowercase letters are allowed).");
            } else if (trimmedCategoryName.length() > 20) {
                // Check if the trimmed category name exceeds the maximum allowed length (20 characters).
                errorList.add("Please provide a category name within 20 characters.");
            }
        }

        // Validate description
        // NOTE: field is optional
        if (Objects.nonNull(description) && description.isBlank()) {
            // Add error message if description is provided but blank
            errorList.add("Please enter a valid description.");
        }

        // Validate image URL
        if (Objects.isNull(imageUrl) || imageUrl.isBlank()) {
            // Check if the image URL is null or blank (includes empty strings and strings with only whitespace).
            errorList.add("Please enter image URL.");
        } else {
            // Trim the URL to remove leading and trailing whitespace.
            String trimmedUrl = imageUrl.trim();

            // Validate the trimmed URL format.
            if (!isValidUrl(trimmedUrl)) {
                // Add an error message if the URL format is invalid.
                errorList.add("Please enter valid URL.");
            } else if (trimmedUrl.length() > 200) {
                // Check if the URL length exceeds the maximum allowed length (200 characters).
                errorList.add("Please provide URL within 200 characters.");
            }
        }

        // Return the list of validation error messages
        return errorList;
    }

    // Method to validate category name, description, and image URL for category update
    public static ArrayList<String> validateCategoryForUpdate(String categoryName, String imageUrl) {
        // Initialize the list to store validation error messages
        ArrayList<String> errorList = new ArrayList<>();

        // Validate category name
        if (Objects.nonNull(categoryName)) {
            // Trim the category name to remove leading and trailing whitespace.
            String trimmedCategoryName = categoryName.trim();
            if (trimmedCategoryName.isEmpty()) {
                // Check if the category name is empty.
                errorList.add("Please enter a category name.");
            } else if (!trimmedCategoryName.matches("^[a-zA-Z ]*$")) {
                // Check if the trimmed category name contains only valid characters (letters and spaces).
                errorList.add("Please enter a valid category name (only uppercase and lowercase letters are allowed).");
            } else if (trimmedCategoryName.length() > 20) {
                // Check if the trimmed category name exceeds the maximum allowed length (20 characters).
                errorList.add("Please provide a category name within 20 characters.");
            }
        }

        // Validate image URL
        if (Objects.nonNull(imageUrl)) {
            // Trim the URL to remove leading and trailing whitespace.
            String trimmedUrl = imageUrl.trim();

            // Validate the trimmed URL format.
            if (!isValidUrl(trimmedUrl)) {
                // Add an error message if the URL format is invalid.
                errorList.add("Please enter valid URL.");
            } else if (trimmedUrl.length() > 200) {
                // Check if the URL length exceeds the maximum allowed length (200 characters).
                errorList.add("Please provide URL within 200 characters.");
            }
        }

        // Return the list of validation error messages
        return errorList;
    }

    /**
     * Validates whether the given string is a properly formatted URL.
     *
     * @param url the string to validate as a URL
     * @return {@code true} if the given string is a valid URL; {@code false} otherwise
     * @throws NullPointerException if the input URL is {@code null}
     */
    // Example validation function
    private static boolean isValidUrl(String url) {
        try {
            new java.net.URL(url); // Checks if the URL is valid
            return true;
        } catch (MalformedURLException e) {
            // Note: The method handles MalformedURLException and mentions potential NullPointerException.
            return false;
        }
    }
}
