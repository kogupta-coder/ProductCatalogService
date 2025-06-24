package com.productCatalog.controllers;

import com.productCatalog.dtos.ExceptionDto;
import com.productCatalog.exceptions.CategoryNotFoundException;
import com.productCatalog.exceptions.ProductNotFoundException;
import com.productCatalog.models.Product;
import com.productCatalog.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    ProductService service;

    ProductController(@Qualifier("selfProductService") ProductService service)
    {
        this.service=service;
    }

//    @GetMapping("/{id}")
//    public Product getSingleProduct(@PathVariable("id") long id)
//    {
//
//        return service.getSingleProduct(id);
//    }


        @GetMapping("/{id}")
    public ResponseEntity<Product> getSingleProduct(@PathVariable("id") long id) throws ProductNotFoundException
    {
        ResponseEntity<Product> response= new ResponseEntity<>(service.getSingleProduct(id),HttpStatus.OK);
//        ResponseEntity<Product> response = null;
//        Product product=null;
//        try{
//            product = service.getSingleProduct(id);
//            response = new ResponseEntity<>(product, HttpStatus.OK);
//
//        }catch(RuntimeException e)
//        {  System.out.println(e.getMessage());
//            response = new ResponseEntity<>(product,HttpStatus.NOT_FOUND);
//        }

        return response;
    }


    @GetMapping("/")
    public List<Product> getAllProducts()
    {
     return service.getAllProducts();
    }

    @PostMapping()
    public Product createProduct(@RequestBody Product product) throws CategoryNotFoundException {
        return  service.createProduct(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") Long id)
    {
        return null;
    }

    //http://localhost:8080/products/title?title=iphone&pageNo=0&pageSize=3
    @GetMapping("/title")
    public Page<Product> getProductByTitle(@RequestParam("title") String title,
                                           @RequestParam("pageNo") int pageNo,
                                           @RequestParam("pageSize") int pageSize) throws ProductNotFoundException {
        Page<Product> products = service.getProductsByTitle(title, pageNo, pageSize);
        if(products==null || products.isEmpty())
        {
            throw new ProductNotFoundException("No Products found with title: " + title);
        }
       return products;
    }



//    //This method will get priority if anything happens within this controller
//    @ExceptionHandler(RuntimeException.class)
//            public ResponseEntity<ExceptionDto> handleRunTimeExceptionWithinController()
//    {
//        ExceptionDto exceptionDto = new ExceptionDto();
//        exceptionDto.setMessage("Something went Wrong from Controller");
//        exceptionDto.setResolutionMethod("Please try again later");
//
//        return new ResponseEntity<>(
//                exceptionDto,
//                HttpStatus.UNAUTHORIZED);
//    }

}
