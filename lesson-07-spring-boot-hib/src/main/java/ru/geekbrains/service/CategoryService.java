package ru.geekbrains.service;

import ru.geekbrains.service.dto.CategoryDto;

public interface CategoryService {

    CategoryDto findById(Long id);

}
