package com.project.code.Repo;

import com.project.code.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    List findAll();

    List findByCategory(String category);

    List findByPriceBetween(Double minPrice,Double maxPrice);

    List findBySku(String sku);

    Product findByName(String name);

    Product findByid(Long id);

    @Query("SELECT i.product FROM Inventory i WHERE i.store.id = :storeId AND LOWER(i.product.name) LIKE LOWER(CONCAT('%', :pname, '%'))")
    List findByNameLike(long storeId,String pname);

    @Query("SELECT i.product FROM Inventory i WHERE i.store.id = :storeId AND LOWER(i.product.name) LIKE LOWER(CONCAT('%', :pname, '%')) AND i.product.category = :category")
    List findByNameAndCategory(long storeId, String pname,String category);

    @Query("SELECT i.product FROM Inventory i WHERE i.store.id = :storeId AND i.product.category = :category")
    List findByCategoryAndStoreId(long storeId,String category);

    @Query("SELECT i FROM Product i WHERE LOWER(i.name) LIKE LOWER(CONCAT('%', :pname, '%'))")
    List findProductBySubName(String pname);

    @Query("SELECT i.product FROM Inventory i WHERE i.store.id = :storeId")
    List findProductsByStoreId(Long storeId);

    @Query("SELECT i.product FROM Inventory i WHERE i.product.category = :category and i.store.id = :storeId")
    List findProductByCategory(String category, long storeId);

    @Query("SELECT i FROM Product i WHERE LOWER(i.name) LIKE LOWER(CONCAT('%', :pname, '%')) AND i.category = :category")
    List findProductBySubNameAndCategory(String pname, String category);


}
