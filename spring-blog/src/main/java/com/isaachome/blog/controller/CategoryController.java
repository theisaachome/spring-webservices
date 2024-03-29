package com.isaachome.blog.controller;

import com.isaachome.blog.payload.CategoryDto;
import com.isaachome.blog.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // get All Categories
    @GetMapping
    public List<CategoryDto> getAllCategories(){
        return categoryService.getAllCategories();
    }
    //add new Category
    @PostMapping
    public ResponseEntity<CategoryDto> addCategory(@RequestBody CategoryDto dto){
        return  new ResponseEntity<>(categoryService.addCategory(dto), HttpStatus.CREATED);
    }
    // update category
    @PutMapping("{id}")
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto dto,@PathVariable("id") long categoryId){
      return  new ResponseEntity<>(categoryService.updateCategory(dto,categoryId),HttpStatus.ACCEPTED);
    }
    // get Category by ID
    @GetMapping("{id}")
    public ResponseEntity<CategoryDto>getCategoryById(@PathVariable("id") long categoryId){
        return  ResponseEntity.ok(categoryService.getCategoryById(categoryId));
    }
    // delete category
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable("id") long categoryId){
        categoryService.deleteCategory(categoryId);
        return  new ResponseEntity<>("Resource deleted successfully",HttpStatus.ACCEPTED);
    }
}
