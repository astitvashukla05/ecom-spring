package com.springboot.ecom_project.Service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.ecom_project.Model.Product;
import com.springboot.ecom_project.Repository.ProductRepo;

@Service
public class ProductService {
    @Autowired
    private ProductRepo repo;

    // Get Products
    public List<Product> getProducts() {
        return repo.findAll();
    }

    // Get one Product
    public Product geProductById(int id) {
        return repo.findById(id).orElse(null);
    }

    // Add New Product
    public Product addProduct(Product prod, MultipartFile imageFile) throws IOException {
        prod.setImageName(imageFile.getOriginalFilename());
        prod.setImageType(imageFile.getContentType());
        prod.setImageData(imageFile.getBytes());
        repo.save(prod);
        return prod;
    }

    // Update Product
    public boolean updateProduct(int id, Product prod, MultipartFile imageFile) {
        Product prod1 = repo.findById(id).orElse(null);
        if (prod1 == null) {
            return false;
        }
        try {
            prod.setImageData(imageFile.getBytes());
            prod.setImageName(imageFile.getOriginalFilename());
            prod.setImageType(imageFile.getContentType());
        } catch (IOException e) {
            return false;
        }
        repo.save(prod);
        return true;

    }

    public boolean deleteProduct(int id) {
        Product prod = repo.findById(id).orElse(null);
        if (prod != null) {
            repo.deleteById(id);
            return true;
        } else
            return false;
    }

}
