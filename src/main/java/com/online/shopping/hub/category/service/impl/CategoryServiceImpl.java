package com.online.shopping.hub.category.service.impl;

import com.online.shopping.hub.category.dao.accessor.CategoryMapperRo;
import com.online.shopping.hub.category.dao.accessor.CategoryMapperRw;
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
    private CategoryMapperRw categoryMapperRw;

    @Autowired
    private CategoryMapperRo categoryMapperRo;

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
            return this.categoryMapperRw.insert(categoryDto);
        } catch (DataAccessException e) {
            logger.error("Failed to create category: {}", e.getMessage(), e);
            throw new CategoryDatabaseException("Failed to create category", e);
        }
    }

    /**
     * Retrieves all categories from the database.
     *
     * @return a list of all categories
     * @throws CategoryDatabaseException if database operation fails
     */
    @Override
    public List<CategoryDto> selectAllCategory() {
        try {
            return this.categoryMapperRo.selectAll();
        } catch (DataAccessException e) {
            logger.error("Failed to fetch categories: {}", e.getMessage(), e);
            throw new CategoryDatabaseException("Failed to fetch categories", e);
        }
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
            CategoryDto existingCategory = this.categoryMapperRo.selectById(categoryId);

            // Throws a CategoryNotFoundException if the category with the specified ID does not exist.
            if (Objects.isNull(existingCategory)) {
                throw new CategoryNotFoundException("Category with ID " + categoryId + " not found");
            }

            // if target category exists
            // Update categoryName only if a new value is provided
            existingCategory.setCategoryName(
                    Objects.nonNull(categoryDto.getCategoryName()) && !categoryDto.getCategoryName().isEmpty() ?
                            categoryDto.getCategoryName() :
                            existingCategory.getCategoryName()
            );

            // Update description only if a new value is provided
            existingCategory.setDescription(
                    Objects.nonNull(categoryDto.getDescription()) && !categoryDto.getDescription().isEmpty() ?
                            categoryDto.getDescription() :
                            existingCategory.getDescription()
            );

            // Update imageUrl only if a new value is provided
            existingCategory.setImageUrl(
                    Objects.nonNull(categoryDto.getImageUrl()) && !categoryDto.getImageUrl().isEmpty() ?
                            categoryDto.getImageUrl() :
                            existingCategory.getImageUrl()
            );

            // Save the updated category details back to the database
            return this.categoryMapperRw.update(existingCategory);
        } catch (DataAccessException e) {
            logger.error("Failed to update category with ID {}: {}", categoryId, e.getMessage(), e);
            throw new CategoryDatabaseException("Failed to update category with ID " + categoryId, e);
        }
    }

    @Override
    public boolean isDuplicateCategory(String categoryName) {
        CategoryDto categoryDto = this.categoryMapperRo.selectByCategoryName(categoryName);
        return Objects.nonNull(categoryDto);
    }
}
