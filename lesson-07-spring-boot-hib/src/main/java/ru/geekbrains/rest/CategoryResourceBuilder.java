package ru.geekbrains.rest;

import ru.geekbrains.service.CategoryService;

public class CategoryResourceBuilder {
    private CategoryService categoryService;

    public CategoryResourceBuilder setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
        return this;
    }

    public CategoryResource createCategoryResource() {
        return new CategoryResource(categoryService);
    }
}