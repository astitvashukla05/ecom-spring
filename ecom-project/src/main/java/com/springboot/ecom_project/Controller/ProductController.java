package com.springboot.ecom_project.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.springboot.ecom_project.Model.Product;
import com.springboot.ecom_project.Service.ProductService;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ProductController {
    @Autowired
    private ProductService service;

    // HOME
    @RequestMapping("/")
    public String greet() {
        return "greeting me";
    }

    // Get All Products
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts() {
        return new ResponseEntity<>(service.getProducts(), HttpStatus.OK);

    }

    // Get One Product
    @GetMapping("/product/{id}")
    public ResponseEntity<Product> geProductById(@PathVariable int id) {
        Product product = service.geProductById(id);
        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(service.geProductById(id), HttpStatus.OK);
    }

    // Add New Product
    @PostMapping("/product")
    public ResponseEntity<?> addProduct(@RequestPart Product product,
            @RequestPart MultipartFile imageFile) {
        try {
            Product prod = service.addProduct(product, imageFile);
            return new ResponseEntity<>(prod, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Get Product Image
    @GetMapping("/product/{id}/image")
    public ResponseEntity<byte[]> getImages(@PathVariable int id) {
        Product prod = service.geProductById(id);
        byte[] imageData = prod.getImageData();
        return ResponseEntity.ok().contentType(MediaType.valueOf(prod.getImageType())).body(imageData);

    }
}
