package ru.geekbrains.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.geekbrains.service.CategoryService;

@Controller
@RequestMapping("/category")
public class CategoryController {

    private CategoryService categoryService;


    public CategoryController() {
    }

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/{id}")
    public String form(Model model, @PathVariable("id") Long id) {
        model.addAttribute("category", categoryService.findById(id));
        return "product_form";
    }
}
