package com.example.spring.mvc.test.springmvc.controllers;

import com.example.spring.mvc.test.springmvc.entities.Product;
import com.example.spring.mvc.test.springmvc.service.ProductService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class IndexControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ProductService productService;

    @InjectMocks
    private IndexController indexController;

    @BeforeEach
    public void setup(){
        //indexController=new IndexController();
        MockitoAnnotations.initMocks(this);
        mockMvc= MockMvcBuilders.standaloneSetup(indexController).build();
    }

    @Test
    void testIndex() throws Exception {

        List<Product> products=new ArrayList<>();
        products.add(new Product());
        products.add(new Product());

        when(productService.listAll()).thenReturn(products);

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attribute("products",hasSize(2))
                );
    }
}