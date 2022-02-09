package ru.geekbrains.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.persist.CategoryRepository;
import ru.geekbrains.service.ProductService;
import ru.geekbrains.service.dto.ProductDto;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Optional;


@Controller
@RequestMapping("/product")
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private final ProductService productService;

    private final CategoryRepository categoryRepository;


    @Autowired
    public ProductController(ProductService productService, CategoryRepository categoryRepository) {
        this.productService = productService;

        this.categoryRepository = categoryRepository;
    }

    @GetMapping
    public String listPage(Model model,
                           @RequestParam("nameFilter") Optional<String> nameFilter,
                           @RequestParam("minPriceFilter") Optional<BigDecimal> minPriceFilter,
                           @RequestParam("maxPriceFilter") Optional<BigDecimal> maxPriceFilter,
                           @RequestParam("page") Optional<Integer> page,
                           @RequestParam("size") Optional<Integer> size,
                           @RequestParam("sort") Optional<String> sort) {

        logger.info("Product filter with name pattern {}", nameFilter.orElse(null));
        logger.info("Product filter with price minPrice {}", minPriceFilter.orElse(null));
        logger.info("Product filter with price maxPrice {}", maxPriceFilter.orElse(null));


            model.addAttribute("products", productService.findAll(
                    nameFilter,
                    minPriceFilter,
                    maxPriceFilter,
                    page.orElse(1) - 1,
                    size.orElse(5),
                    sort.orElse("id"))
            );
        return"product";
    }


    @GetMapping("/{id}")
    public String form(Model model, @PathVariable("id") Long id) {
        model.addAttribute("product", productService.findById(id).
                orElseThrow(() -> new NotFoundException("Product not found")));
        model.addAttribute("categories", categoryRepository.findAll());
        return "product_form";
    }

    @PostMapping
    public String save(@Valid ProductDto product, BindingResult result) {
        if (result.hasErrors()) {
            return "product_form";
        }
        productService.save(product);
        return "redirect:/product";
    }
    @GetMapping("/new")
    public String addProduct(Model model) {
        model.addAttribute("product", new ProductDto());
        model.addAttribute("categories", categoryRepository.findAll());
        return "product_form";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        productService.deleteById(id);
        return "redirect:/product";
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFoundExceptionHandler(NotFoundException e, Model model) {
        model.addAttribute("message", e.getMessage());
        return "not_found";
    }
}
