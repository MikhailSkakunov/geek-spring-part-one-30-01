package ru.geekbrains.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.service.CategoryService;
import ru.geekbrains.service.dto.CategoryDto;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryResource {

    private final CategoryService categoryService;

    @Autowired
    public CategoryResource(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/{id}")
    public CategoryDto findOne(@PathVariable("id") Long id) {
        return categoryService.findById(id);
    }


}
