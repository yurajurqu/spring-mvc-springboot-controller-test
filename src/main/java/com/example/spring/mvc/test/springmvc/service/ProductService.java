package com.example.spring.mvc.test.springmvc.service;

import com.example.spring.mvc.test.springmvc.entities.Product;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductService {

    public List<Product> listAll(){
        return null;
    };

    public Product getById(Long id) {
        return null;
    }

    public Product save(Product product) {
        return product;
    }
}
