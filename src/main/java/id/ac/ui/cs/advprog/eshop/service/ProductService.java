package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import java.util.List;

public interface ProductService {
    public Product create(Product product);
    Product update(Product product);
    public List<Product> findAll();
    Product getById(String productId);
    void delete(String productId);

}