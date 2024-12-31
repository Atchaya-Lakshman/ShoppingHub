package com.online.shopping.hub.category.service;

import com.online.shopping.hub.category.dao.entity.CategoryDto;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service interface for managing Category operations.
 * Defines methods for creating, retrieving, and updating category data.
 */
@Service
public interface CategoryService {

    /**
     * Creates a new category with the given details.
     *
     * @param categoryDto the details of the category to create
     * @return the number of rows affected by the operation
     */
    int createCategory(CategoryDto categoryDto);

    /**
     * Retrieves all available categories.
     *
     * @return a list of all categories
     */
    List<CategoryDto> selectAllCategory();

    /**
     * Updates the details of a specific category.
     *
     * @param categoryId  the ID of the category to update
     * @param categoryDto the updated details of the category
     * @return the number of rows affected by the operation
     */
    int updateCategory(int categoryId, CategoryDto categoryDto);
}
