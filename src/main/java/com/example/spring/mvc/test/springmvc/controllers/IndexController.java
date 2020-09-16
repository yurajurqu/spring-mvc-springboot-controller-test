package com.example.spring.mvc.test.springmvc.controllers;

import com.example.spring.mvc.test.springmvc.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @Autowired
    private ProductService productService;

    @RequestMapping("/")
    public String index(Model model){
        productService.listAll();
        model.addAttribute("products",productService.listAll());
        return "index";
    }
}
