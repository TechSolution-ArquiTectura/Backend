package com.upc.TuCine.service.impl;

import com.upc.TuCine.dto.CategoryDto;
import com.upc.TuCine.dto.receive.CategoryReceiveDto;
import com.upc.TuCine.model.Category;
import com.upc.TuCine.repository.CategoryRepository;
import com.upc.TuCine.service.CategoryService;
import com.upc.TuCine.shared.exception.ResourceValidationException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {


    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    CategoryServiceImpl(){
        this.modelMapper = new ModelMapper();
    }

    public CategoryDto EntityToDto(Category category){
        return modelMapper.map(category, CategoryDto.class);
    }

    public Category DtoToEntity(CategoryDto categoryDto){
        return modelMapper.map(categoryDto, Category.class);
    }


    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(this::EntityToDto)
                .collect(Collectors.toList());
    }
    @Override
    public CategoryDto createCategory(CategoryReceiveDto categoryReceiveDto) {

        CategoryDto categoryDto = modelMapper.map(categoryReceiveDto, CategoryDto.class);

        validateCategory(categoryDto);
        existsCategoryByName(categoryDto.getName());

        Category category = DtoToEntity(categoryDto);
        return EntityToDto(categoryRepository.save(category));
    }

    private void validateCategory(CategoryDto category) {
        if (category.getName() == null || category.getName().isEmpty()) {
            throw new ResourceValidationException("El nombre de la categoria es obligatorio");
        }
    }

    private void existsCategoryByName(String name) {
        if (categoryRepository.existsByName(name)) {
            throw new ResourceValidationException("No se puede registrar la categoria, ya existe una con ese nombre");
        }
    }


}
