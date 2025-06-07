package com.productCatalog.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.productCatalog.models.Product;
import com.productCatalog.repository.ProductRepository;
import com.productCatalog.services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.any;

@WebMvcTest(ProductController.class)
class ProductControllerMockMvcTest {

    @MockitoBean(name = "selfProductService")
    ProductService service;

    @Autowired
    ProductController controller;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockitoBean
    ProductRepository productRepository;

    @Test
    public void getAllProductsTest() throws Exception {
        Product p1 = new Product();
        p1.setTitle("Iphone");

        Product p2 = new Product();
        p2.setTitle("Mac Book");

        List<Product> productList = new ArrayList<>();
        productList.add(p1);
        productList.add(p2);

        when(service.getAllProducts()).thenReturn(productList);

        String expectedJson = mapper.writeValueAsString(productList);
        mockMvc.perform(
                MockMvcRequestBuilders.get("/products/")

        ).andExpect(content().json(expectedJson));
    }

    @Test
    public void getSingleProductTest() throws Exception {
        Long id =10l;
        Product p1 = new Product();
        p1.setTitle("Iphone");

        when(service.getSingleProduct(10l)).thenReturn(p1);

        String expectedJson = mapper.writeValueAsString(p1);
        mockMvc.perform(
                MockMvcRequestBuilders.get("/products/{id}", 10L)

        ).andExpect(content().json(expectedJson));
    }

    @Test
    public void createProductTest() throws Exception {
        Long id =10l;
        Product p1 = new Product();
        p1.setTitle("Iphone");

        when(service.createProduct(any(Product.class))).thenReturn(p1);

        String expectedJson = mapper.writeValueAsString(p1);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/products")
                        .contentType(MediaType.APPLICATION_JSON)   // Tell Spring we're sending JSON
                        .content(expectedJson))
                        .andExpect(status().isOk())                 // Expect 200 OK or 201 Created
                .andExpect(content().json(expectedJson));
    }

}