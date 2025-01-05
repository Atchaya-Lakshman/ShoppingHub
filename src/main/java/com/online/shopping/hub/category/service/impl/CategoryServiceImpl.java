package com.online.shopping.hub.category.service.impl;

import com.online.shopping.hub.category.dao.accessor.CategoryMapper;
import com.online.shopping.hub.category.dao.entity.CategoryDto;
import com.online.shopping.hub.category.exception.CategoryDatabaseException;
import com.online.shopping.hub.category.exception.CategoryNotFoundException;
import com.online.shopping.hub.category.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;

/**
 * Implementation of the CategoryService interface for handling category operations.
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    // Logger for logging messages and errors
    Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * Creates a new category.
     *
     * @param categoryDto the details of the category to be created
     * @return the number of rows affected
     * @throws CategoryDatabaseException if database operation fails
     */
    @Override
    public int createCategory(CategoryDto categoryDto) {
        try {
            return this.categoryMapper.insert(categoryDto);
        } catch (DataAccessException e) {
            logger.error("Failed to create category: {}", e.getMessage(), e);
            throw new CategoryDatabaseException("Failed to create category", e);
        }
    }

    /**
     * Retrieves all categories from the database.
     *
     * @return a list of all categories
     */
    @Override
    public List<CategoryDto> selectAllCategory() {
        logger.info("Fetching all categories from the database.");
        return this.categoryMapper.selectAll();
    }

    /**
     * Updates an existing category based on its ID.
     *
     * @param categoryId  the ID of the category to update
     * @param categoryDto the updated details of the category
     * @return the number of rows affected
     * @throws CategoryNotFoundException if the specified category ID does not exist
     * @throws CategoryDatabaseException if there is an error accessing the database during the update operation
     */
    @Override
    public int updateCategory(int categoryId, CategoryDto categoryDto) {
        logger.info("Updating category with ID: {}", categoryId);

        try {
            // Fetch the existing category details from the database using its ID
            CategoryDto category = this.categoryMapper.selectById(categoryId);

            // Throws a CategoryNotFoundException if the category with the specified ID does not exist.
            if (Objects.isNull(category)) {
                throw new CategoryNotFoundException("Category with ID " + categoryId + " not found");
            }

            // if target category exists
            // Update categoryName only if a new value is provided
            category.setCategoryName(Objects.nonNull(categoryDto.getCategoryName()) ? categoryDto.getCategoryName() : category.getCategoryName());
            // Update description only if a new value is provided
            category.setDescription(Objects.nonNull(categoryDto.getDescription()) ? categoryDto.getDescription() : category.getDescription());
            // Update imageUrl only if a new value is provided
            category.setImageUrl(Objects.nonNull(categoryDto.getImageUrl()) ? categoryDto.getImageUrl() : category.getImageUrl());

            // Save the updated category details back to the database
            return this.categoryMapper.update(category);
        } catch (DataAccessException e) {
            logger.error("Failed to update category with ID {}: {}", categoryId, e.getMessage(), e);
            throw new CategoryDatabaseException("Failed to update category with ID " + categoryId, e);
        }
    }
}
