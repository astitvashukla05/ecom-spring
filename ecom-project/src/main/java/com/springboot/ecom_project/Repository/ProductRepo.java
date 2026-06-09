package com.springboot.ecom_project.Repository;

import org.springframework.stereotype.Repository;

import com.springboot.ecom_project.Model.Product;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {

}
