package ru.geekbrains.service;

import org.springframework.data.domain.Page;
import ru.geekbrains.service.dto.ProductDto;

import java.math.BigDecimal;
import java.util.Optional;

public interface ProductService {

    Page<ProductDto> findAll(Optional<String> nameFilter, Optional<BigDecimal> minPriceFilter,
                             Optional<BigDecimal> maxPriceFilter, Integer page, Integer size, String sort);

    Optional<ProductDto> findById(Long id);

    ProductDto save(ProductDto product);

    void deleteById(Long id);
}
