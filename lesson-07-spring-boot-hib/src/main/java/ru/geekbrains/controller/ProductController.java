package ru.geekbrains.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.persist.Product;
import ru.geekbrains.persist.ProductRepository;
import ru.geekbrains.persist.ProductSpecification;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Optional;


@Controller
@RequestMapping("/product")
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private final ProductRepository productRepository;


    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;

    }

    @GetMapping
    public String listPage(Model model,
                           @RequestParam("nameFilter") Optional<String> nameFilter,
                           @RequestParam("minPrice") Optional<BigDecimal> minPrice,
                           @RequestParam("maxPrice") Optional<BigDecimal> maxPrice
    ) {

        logger.info("Product filter with name pattern {}", nameFilter.orElse(null));
        logger.info("Product filter with price minPrice {}", minPrice.orElse(null));
        logger.info("Product filter with price maxPrice {}", maxPrice.orElse(null));

        Specification<Product> specification = Specification.where(null);

        if (nameFilter.isPresent() && !((nameFilter.get().isBlank()))) {
            specification = specification.and(ProductSpecification.nameLike(nameFilter.get()));
        }

        else if (nameFilter.isPresent() && new BigDecimal(nameFilter.get()).compareTo(BigDecimal.ZERO) >= 0) {
            specification = specification.and(ProductSpecification.minPriceFilter(new BigDecimal(nameFilter.get())));
        }

        else if (nameFilter.isPresent() && new BigDecimal(nameFilter.get()).compareTo(BigDecimal.ZERO) >= 0) {
            specification = specification.and(ProductSpecification.maxPriceFilter(new BigDecimal(nameFilter.get())));
        }
            model.addAttribute("products",productRepository.findAll(specification));
        return"product";
    }


    @GetMapping("/{id}")
    public String form(Model model, @PathVariable("id") long id) {
        model.addAttribute("product", productRepository.findById(id).
                orElseThrow(() -> new NotFoundException("Product not found")));
        return "product_form";
    }

    @PostMapping
    public String saveProduct(@Valid Product product) {
        productRepository.save(product);
        return "redirect:/product";
    }

    @GetMapping("/new")
    public String addProduct(Model model) {
        model.addAttribute("product", new Product());
        return "product_form";
    }

    @PostMapping("/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
                productRepository.deleteById(id);
        return "redirect:/product";
    }


    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFoundExceptionHandler(NotFoundException e, Model model) {
        model.addAttribute("message", e.getMessage());
        return "not_found";
    }
}
