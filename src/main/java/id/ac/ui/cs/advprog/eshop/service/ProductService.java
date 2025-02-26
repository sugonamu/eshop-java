package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import java.util.List;

public interface ProductService extends ProductCreationService, ProductRetrievalService, ProductUpdateService, ProductDeletionService {
}

interface ProductCreationService {
    Product create(Product product);
}

interface ProductRetrievalService {
    List<Product> findAll();
}

interface ProductUpdateService {
    Product update(Product product);
}

interface ProductDeletionService {
    void delete(String productId);
}