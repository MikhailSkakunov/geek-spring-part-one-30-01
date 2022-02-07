package ru.geekbrains.persist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

//    List<Product> findAllByNameLike(String pattern);

//    @Query("select p " +
//            "from Product p " +
//            "where (p.name like :pattern or :pattern is null)")
////    and " +
////            " (p.price >= :minPrice or :minPrice is null) and " +
////            " (p.price <= :maxPrice or :maxPrice is null)")
//    List<Product> findByFilter(@Param("pattern") String pattern);
//    List<Product> findMinPrice(@Param("minPrice")String minPrice);
//    List<Product> findMaxPrice(@Param("maxPrice")BigDecimal maxPrice);


//    static void findAllMinMaxProducts() {
//        EntityManager em = null;
//
//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<Product> productCriteria = cb.createQuery(Product.class);
//        Root<Product> productRoot = productCriteria.from(Product.class);
//
//        List<Predicate> predicates = new ArrayList<>();
//        predicates.add(cb.equal(productRoot.get("title"), Product.class));
//        predicates.add(cb.le(productRoot.get("price"), 159));
//        predicates.add(cb.ge(productRoot.get("price"), 90));
//
//        List<Product> products = em.createQuery(productCriteria.select(productRoot.get("price")).select(productRoot.get("title"))
//                        .where(predicates.toArray(new Predicate[0])))
//                .getResultList();
//        System.out.println(products);
//
//    }
}
