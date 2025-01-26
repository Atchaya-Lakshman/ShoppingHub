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
public interface CategoryMapperRw {

    /**
     * Inserts a new category record into the database.
     *
     * @param categoryDto the category details to be inserted.
     * @return the number of rows affected.
     */
    int insert(@Param("categoryDto") CategoryDto categoryDto);

    /**
     * Updates an existing category record in the database.
     *
     * @param categoryDto the updated category details.
     * @return the number of rows affected.
     */
    int update(@Param("categoryDto") CategoryDto categoryDto);
}
