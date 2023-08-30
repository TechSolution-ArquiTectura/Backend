package com.upc.TuCine.service;

import com.upc.TuCine.dto.CategoryDto;
import com.upc.TuCine.dto.receive.CategoryReceiveDto;

import java.util.List;

public interface CategoryService {

    List<CategoryDto> getAllCategories();
    CategoryDto createCategory(CategoryReceiveDto categoryReceiveDto);
}
