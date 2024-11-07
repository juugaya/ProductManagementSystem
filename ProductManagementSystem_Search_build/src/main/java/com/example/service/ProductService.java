package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.model.entity.Product;
import com.example.repository.ProductRepository;
@Service
public class ProductService {
	@Autowired
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Transactional
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Transactional
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    @Transactional
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
    
    //moto
//    @Transactional
//    public List<Product> searchProducts(String searchQuery) {
//        return productRepository.findByNameContaining(searchQuery);
//    }
    
    public List<Product> searchProducts(String keyword) {
    	return productRepository.findAll().stream() .filter(product -> product.getName().contains(keyword) || product.getDescription().contains(keyword) || product.getPrice().toString().contains(keyword)) .toList(); }
}
