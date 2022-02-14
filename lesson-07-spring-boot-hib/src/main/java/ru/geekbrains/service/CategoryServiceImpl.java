package ru.geekbrains.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.persist.Category;
import ru.geekbrains.persist.CategoryRepository;
import ru.geekbrains.service.dto.CategoryDto;

@Service
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryDto findById(Long id) {
        return CategoryServiceImpl.convertToDto(categoryRepository.getById(id));
    }

    private static CategoryDto convertToDto(Category category) {
        return new CategoryDto(
                category.getId(),
                category.getName());
    }
}
