package com.online.shopping.hub.category.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

/**
 * Data Transfer Object (DTO) representing a Category entity.
 * Encapsulates category-related data for transfer between layers.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDto {
    private int id;
    private String categoryName;
    private String description;
    private String imageUrl;
    private Date categoryItemUpdatedAt;
    private Date createdAt;
    private Date updatedAt;
}
