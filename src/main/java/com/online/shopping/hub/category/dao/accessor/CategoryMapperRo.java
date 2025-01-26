package com.online.shopping.hub.category.dao.accessor;

import com.online.shopping.hub.category.dao.entity.CategoryDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Interface for Category database operations using MyBatis.
 * Provides methods to perform CRUD operations on the Category entity.
 */
@Mapper
public interface CategoryMapperRo {

    /**
     * Retrieves all category records from the database.
     *
     * @return a list of all categories.
     */
    List<CategoryDto> selectAll();

    /**
     * Retrieves a category record by its ID.
     *
     * @param id the ID of the category to retrieve.
     * @return the category details or null if not found.
     */
    CategoryDto selectById(@Param("id") int id);

    /**
     * Retrieves a category record by category name.
     *
     * @param categoryName category name
     * @return CategoryDto
     */
    CategoryDto selectByCategoryName(@Param("categoryName") String categoryName);
}
