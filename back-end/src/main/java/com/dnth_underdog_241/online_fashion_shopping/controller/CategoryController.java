package com.dnth_underdog_241.online_fashion_shopping.controller;


import com.dnth_underdog_241.online_fashion_shopping.dto.response.CategoryGetDto;
import com.dnth_underdog_241.online_fashion_shopping.exception.ResourcesNotFound;
import com.dnth_underdog_241.online_fashion_shopping.mapper.CategoryMapper;
import com.dnth_underdog_241.online_fashion_shopping.model.Category;
import com.dnth_underdog_241.online_fashion_shopping.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("api/v1/categories")
@RequiredArgsConstructor
public class CategoryController
{
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;


    @GetMapping
    ResponseEntity<List<CategoryGetDto>> getAllCategory()
    {
        List<Category> categories = categoryRepository.findAll();
        return
                ResponseEntity
                        .status(HttpStatus.OK)
                        .body
                                (
                                        categories
                                                .stream()
                                                .map(categoryMapper::toDto)
                                                .collect(Collectors.toList())
                                );
    }


    @GetMapping("{id}/sub")
    ResponseEntity<List<CategoryGetDto>> getSubcategory(@PathVariable Long id)
    {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body
                        (
                                categoryRepository
                                        .findById(id)
                                        .orElseThrow
                                                (
                                                        () ->
                                                                new ResourcesNotFound("Category not found")
                                                )
                                        .getChildren()
                                        .stream()
                                        .map(categoryMapper::toDto)
                                        .collect(Collectors.toList())
                        );
    }
}