package com.example.spring.mvc.test.springmvc.controllers;

import com.example.spring.mvc.test.springmvc.entities.Product;
import com.example.spring.mvc.test.springmvc.service.ProductService;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.isNotNull;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ProductControllerTest {
    private MockMvc mockMvc;
    @Mock
    private ProductService productService;
    @InjectMocks
    private ProductController productController;
    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
        mockMvc= MockMvcBuilders.standaloneSetup(productController).build();
    }
    @Test
    public void list() throws Exception {
        List<Product> products= new ArrayList<>();
        products.add(new Product());
        products.add(new Product());
        when(productService.listAll()).thenReturn(products);
        mockMvc.perform(get("/product/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("product/list"))
                .andExpect(model().attribute("products",hasSize(2)));
    }
    @Test
    public void show() throws Exception {
        String name = "Galaxy S10 Lite";
        when(productService.getById(Mockito.anyLong())).thenReturn(new Product(name));
        mockMvc.perform(get("/product/show/3"))
                .andExpect(status().isOk())
                .andExpect(view().name("product/show"))
                .andExpect(model().attribute("product",instanceOf(Product.class)))
                .andExpect(model().attribute("product",hasProperty("name",is(name))))
        ;
    }

    @Test
    public void create() throws Exception {

        String name = "My New product";
        when(productService.save(Mockito.any(Product.class))).thenReturn(new Product(name));

        mockMvc.perform(post("/product/new")
                .param("name", name))
                .andExpect(model().attribute("product",hasProperty("name",is(name))))
        ;
        ArgumentCaptor<Product> productArgumentCaptor = ArgumentCaptor.forClass(Product.class);
        verify(productService).save(productArgumentCaptor.capture());
        Product productArgument = productArgumentCaptor.getValue();
        assertEquals(name,productArgument.getName());


    }

}