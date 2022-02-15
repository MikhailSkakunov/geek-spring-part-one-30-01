package ru.geekbrains.persist;

import org.springframework.stereotype.Repository;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

//@Repository
//public class ProductRepositoryImpl implements ProductRepository{
//
//    @PersistenceContext
//    private EntityManager em;
//
//
//    @Override
//    public List<Product> findAll() {
//        return new ArrayList<>(productMap.values());
//    }
//
//    @Override
//    public Optional<Product> findById(long id) {
//        return Optional.ofNullable(productMap.get(id));
//    }
//
//    @Override
//    public void save(Product product) {
//        if (product.getId() == null) {
//            long id = identity.incrementAndGet();
//            product.setId(id);
//        }
//        productMap.put(product.getId(), product);
//    }
//
//    @Override
//    public void delete(Product id) {
//        productMap.remove(id);
//    }
//}