package com.online.shopping.hub.category.service.impl;

import com.online.shopping.hub.category.dao.accessor.CategoryMapper;
import com.online.shopping.hub.category.dao.entity.CategoryDto;
import com.online.shopping.hub.category.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
     * Creates a new category in the database.
     *
     * @param categoryDto the details of the category to create
     * @return the number of rows affected
     */
    @Override
    public int createCategory(CategoryDto categoryDto) {
        return this.categoryMapper.insert(categoryDto);
    }

    /**
     * Retrieves all categories from the database.
     *
     * @return a list of all categories
     */
    @Override
    public List<CategoryDto> selectAllCategory() {
        return this.categoryMapper.selectAll();
    }

    /**
     * Updates an existing category based on its ID.
     *
     * @param categoryId  the ID of the category to update
     * @param categoryDto the updated details of the category
     * @return the number of rows affected
     */
    @Override
    public int updateCategory(int categoryId, CategoryDto categoryDto) {
        // Fetch the existing category details from the database using its ID
        CategoryDto category = this.categoryMapper.selectById(categoryId);

        // Check if the category exists
        if (Objects.nonNull(category)) {
            // Update categoryName only if a new value is provided
            category.setCategoryName(Objects.nonNull(categoryDto.getCategoryName()) ? categoryDto.getCategoryName() : category.getCategoryName());
            // Update description only if a new value is provided
            category.setDescription(Objects.nonNull(categoryDto.getDescription()) ? categoryDto.getDescription() : category.getDescription());
            // Update imageUrl only if a new value is provided
            category.setImageUrl(Objects.nonNull(categoryDto.getImageUrl()) ? categoryDto.getImageUrl() : category.getImageUrl());

            // Save the updated category details back to the database
            return this.categoryMapper.update(category);
        } else {
            // Log an error message if the category with the given ID does not exist
            logger.error("Error while updating category, category ID = {}", categoryId);
            return 0; // NOTE: Return 0 to indicate no update was made
        }
    }
}
