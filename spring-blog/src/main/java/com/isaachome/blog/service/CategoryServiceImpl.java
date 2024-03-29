package com.isaachome.blog.service;

import com.isaachome.blog.entity.Category;
import com.isaachome.blog.exception.ResourceNotFoundException;
import com.isaachome.blog.payload.CategoryDto;
import com.isaachome.blog.repos.CategoryRepos;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements  CategoryService{

    private  final CategoryRepos categoryRepos;
    private  final ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepos categoryRepos, ModelMapper modelMapper) {
        this.categoryRepos = categoryRepos;
        this.modelMapper = modelMapper;
    }

    @Override
    public CategoryDto addCategory(CategoryDto dto) {
        Category category = modelMapper.map(dto,Category.class);
        var savedCategory = categoryRepos.save(category);
        return  modelMapper.map(savedCategory,CategoryDto.class);
    }

    @Override
    public CategoryDto getCategoryById(long categoryId) {
        var category = categoryRepos.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","ID",categoryId));
        return modelMapper.map(category,CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        return categoryRepos.findAll().stream().map(data->modelMapper.map(data,CategoryDto.class)).collect(Collectors.toList());
    }

    @Override
    public CategoryDto updateCategory(CategoryDto dto, long id) {
        var category = categoryRepos.findById(id).orElseThrow(()-> new ResourceNotFoundException("Category","ID",id));
        category.setName(dto.getName());
        category.setDescription(dto.getDescription());
        category.setId(id);
        var updatedCategory = categoryRepos.save(category);
        return modelMapper.map(updatedCategory,CategoryDto.class);
    }

    @Override
    public void deleteCategory(long id) {
        var category= categoryRepos.findById(id).orElseThrow(()-> new ResourceNotFoundException("Category","ID",id));
       categoryRepos.delete(category);
    }
}
