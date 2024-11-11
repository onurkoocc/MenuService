package com.qrmenu.MenuService.domain.controller;



import com.qrmenu.MenuService.domain.model.request.ProductRequest;
import com.qrmenu.MenuService.domain.model.response.ProductResponse;
import com.qrmenu.MenuService.domain.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    @PreAuthorize("hasRole('RESTAURANT_MANAGER')")
    public ResponseEntity<ProductResponse> createProduct(@Valid @RequestBody ProductRequest productRequest) {
        ProductResponse productResponse = productService.createProduct(productRequest);
        return ResponseEntity.ok(productResponse);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Long productId) {
        ProductResponse productResponse = productService.getProductById(productId);
        return ResponseEntity.ok(productResponse);
    }

    @PutMapping
    @PreAuthorize("hasRole('RESTAURANT_MANAGER')")
    public ResponseEntity<ProductResponse> updateProduct(@Valid @RequestBody ProductRequest productRequest) {
        ProductResponse productResponse = productService.updateProduct(productRequest);
        return ResponseEntity.ok(productResponse);
    }

    @DeleteMapping("/{productId}")
    @PreAuthorize("hasRole('RESTAURANT_MANAGER')")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }
}
