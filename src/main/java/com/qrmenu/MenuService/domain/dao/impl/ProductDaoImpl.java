package com.qrmenu.MenuService.domain.dao.impl;

import com.qrmenu.MenuService.config.exception.ResourceNotFoundException;
import com.qrmenu.MenuService.domain.dao.ProductDao;
import com.qrmenu.MenuService.domain.mapper.MenuMapper;
import com.qrmenu.MenuService.domain.model.entity.*;
import com.qrmenu.MenuService.domain.model.request.ProductRequest;
import com.qrmenu.MenuService.domain.model.response.ProductResponse;
import com.qrmenu.MenuService.domain.repository.ProductRepository;
import com.qrmenu.MenuService.domain.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ProductDaoImpl implements ProductDao {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public ProductResponse createProduct(ProductRequest productRequest) {
        // Map DTO to Entity
        Product product = MenuMapper.productRequestToProduct(productRequest);

        // Set Category
        if (productRequest.getCategoryId() == null) {
            throw new IllegalArgumentException("Category ID is required for products");
        }
        Category category = categoryRepository.findById(productRequest.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + productRequest.getCategoryId()));
        product.setCategory(category);

        // Save the product
        Product savedProduct = productRepository.save(product);

        // Map Entity to DTO
        return MenuMapper.productToProductResponse(savedProduct);
    }

    @Override
    public ProductResponse getProductById(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + productId));

        return MenuMapper.productToProductResponse(product);
    }

    @Override
    @Transactional
    public ProductResponse updateProduct(ProductRequest productRequest) {
        if (productRequest.getId() == null) {
            throw new IllegalArgumentException("Product ID is required for update");
        }

        Product product = productRepository.findById(productRequest.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + productRequest.getId()));

        // Update product fields
        MenuMapper.updateProductFromRequest(productRequest, product);

        // Update Category if needed
        if (productRequest.getCategoryId() != null && !productRequest.getCategoryId().equals(product.getCategory().getId())) {
            Category newCategory = categoryRepository.findById(productRequest.getCategoryId())
                    .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + productRequest.getCategoryId()));
            product.setCategory(newCategory);
        }

        // Save the updated product
        Product updatedProduct = productRepository.save(product);

        return MenuMapper.productToProductResponse(updatedProduct);
    }

    @Override
    @Transactional
    public void deleteProduct(Long productId) {
        if (!productRepository.existsById(productId)) {
            throw new ResourceNotFoundException("Product not found with id: " + productId);
        }
        productRepository.deleteById(productId);
    }
}
