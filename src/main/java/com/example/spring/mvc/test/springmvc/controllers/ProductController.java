package com.example.spring.mvc.test.springmvc.controllers;

import com.example.spring.mvc.test.springmvc.entities.Product;
import com.example.spring.mvc.test.springmvc.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping("product/list")
    public String listAll(Model model){
        model.addAttribute("products",productService.listAll());
        return "product/list";
    }
    @RequestMapping("product/show/{id}")
    public String show(Model model, @PathVariable("id") Long id){
        model.addAttribute("product",productService.getById(id));
        return "product/show";
    }

    @RequestMapping(method = RequestMethod.POST,value = "product/new")
    public String create(Model model,   Product product){
        System.out.println(product.getName()+"   xxxxxxxxxxxx");
        model.addAttribute("product",productService.save(product));
        return "list/all";
    }
}
