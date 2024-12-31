package com.online.shopping.hub.category.controller;

import com.online.shopping.hub.category.dao.entity.CategoryDto;
import com.online.shopping.hub.category.service.CategoryService;
import com.online.shopping.hub.util.common.CommonResponse;
import com.online.shopping.hub.util.common.ResponseUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for managing category-related operations.
 * Provides endpoints for creating, retrieving, and updating categories.
 */
@RestController
public class CategoryController {

    /**
     * Handles category operations.
     */
    @Autowired
    CategoryService categoryService;

    /**
     * Creates a new category.
     *
     * @param categoryDto The category details to save.
     * @return Response with the status of the operation.
     */
    @PostMapping("/category/create")
    public ResponseEntity<CommonResponse> createCategory(@RequestBody CategoryDto categoryDto) {
        CommonResponse commonResponse;
        try {
            // Persist the category in the database.
            int insertedCount = this.categoryService.createCategory(categoryDto);

            // Return success response if insertion is successful
            commonResponse = ResponseUtility.getResponse(HttpStatus.CREATED.toString(), insertedCount,
                    "Successfully Created Category");
            return new ResponseEntity<>(commonResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            // Handle any exceptions that occur during the category creation process.
            commonResponse = ResponseUtility.getResponse(HttpStatus.INTERNAL_SERVER_ERROR.toString(), null,
                    "Error while creating category : " + e);
            return new ResponseEntity<>(commonResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Retrieves a list of all categories.
     *
     * @return Response with the list of categories.
     */
    @GetMapping("/category/list")
    public ResponseEntity<CommonResponse> listCategory() {
        CommonResponse commonResponse;
        try {
            // Retrieve the list of all categories from the database.
            List<CategoryDto> categoryDtoList = this.categoryService.selectAllCategory();

            commonResponse = ResponseUtility.getResponse(HttpStatus.CREATED.toString(), categoryDtoList,
                    "Successfully retrieved all categories");
            return new ResponseEntity<>(commonResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            // Handle any exceptions that occur while retrieving the categories.
            commonResponse = ResponseUtility.getResponse(HttpStatus.INTERNAL_SERVER_ERROR.toString(), null,
                    "Failed to retrieve all categories : " + e);
            return new ResponseEntity<>(commonResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Updates an existing category.
     *
     * @param categoryId  The ID of the category to update.
     * @param categoryDto The updated category details.
     * @return Response with the status of the operation.
     */
    @PutMapping("/category/update/{categoryId}")
    public ResponseEntity<CommonResponse> updateCategory(@PathVariable("categoryId") int categoryId, @RequestBody CategoryDto categoryDto) {
        CommonResponse commonResponse;
        try {
            // Update the category with the given ID.
            int updatedCount = this.categoryService.updateCategory(categoryId, categoryDto);

            // Return response based on DB update
            if (updatedCount == 1) {
                // Successfully updated the category.
                commonResponse = ResponseUtility.getResponse(HttpStatus.CREATED.toString(), updatedCount,
                        "Successfully Updated Category");
                return new ResponseEntity<>(commonResponse, HttpStatus.CREATED);
            } else {
                // No category found to update.
                commonResponse = ResponseUtility.getResponse(HttpStatus.NOT_FOUND.toString(), updatedCount,
                        "Could not find target category to update");
                return new ResponseEntity<>(commonResponse, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            // Handle any exceptions that occur during the update process.
            commonResponse = ResponseUtility.getResponse(HttpStatus.INTERNAL_SERVER_ERROR.toString(), null,
                    "Error while updating category : " + e);
            return new ResponseEntity<>(commonResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
