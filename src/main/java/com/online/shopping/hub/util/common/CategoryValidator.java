package com.online.shopping.hub.util.common;

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
            // Add error message if category name is null or blank
            errorList.add("Please enter category name.");
        } else if (!categoryName.matches("^[a-zA-Z ]*$")) {
            // Add error message if category name contains invalid characters
            errorList.add("Please enter a valid category name (Uppercase and lowercase letters are allowed).");
        }

        // Validate description
        // NOTE: field is optional
        if (Objects.nonNull(description) && description.isBlank()) {
            // Add error message if description is provided but blank
            errorList.add("Please enter a valid description.");
        }

        // URL validation pattern
        String urlPattern = "^(https?|ftp)://[^\\s/$.?#].\\S*$";

        // Validate image URL
        if (Objects.isNull(imageUrl) || imageUrl.isBlank()) {
            // Add error message if image URL is null or blank
            errorList.add("Please enter image URL.");
        } else if (!imageUrl.matches(urlPattern)) {
            // Add error message if image URL does not match the pattern
            errorList.add("Please enter valid image URL.");
        }

        // Return the list of validation error messages
        return errorList;
    }

    // Method to validate category name, description, and image URL for category update
    public static ArrayList<String> validateCategoryForUpdate(String categoryName, String imageUrl) {
        // Initialize the list to store validation error messages
        ArrayList<String> errorList = new ArrayList<>();

        // Validate category name
        if (Objects.nonNull(categoryName) && !categoryName.strip().matches("^[a-zA-Z ]*$")) {
            // Add error message if category name contains invalid characters
            errorList.add("Please enter a valid category name (Uppercase and lowercase letters are allowed).");
        }

        // URL validation pattern
        String urlPattern = "^(https?|ftp)://[^\\s/$.?#].\\S*$";

        // Validate image URL
        if (Objects.nonNull(imageUrl) && !imageUrl.isEmpty() && !imageUrl.matches(urlPattern)) {
            // Add error message if image URL does not match the pattern
            errorList.add("Please enter valid image URL.");
        }

        // Return the list of validation error messages
        return errorList;
    }
}
