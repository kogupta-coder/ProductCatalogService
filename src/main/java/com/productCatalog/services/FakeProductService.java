package com.productCatalog.services;

import com.productCatalog.dtos.FakeStoreProductDto;
import com.productCatalog.exceptions.ProductNotFoundException;
import com.productCatalog.models.Category;
import com.productCatalog.models.Product;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("fakeProductService")
public class FakeProductService implements  ProductService {
    RestTemplate template;
    FakeProductService(RestTemplate template)
    {
        this.template=template;
    }
//    @Override
//    public Product getSingleProduct(Long id) {
//        ResponseEntity<FakeStoreProductDto> resp= template.getForEntity("https://fakestoreapi.com/products/"+id,FakeStoreProductDto.class);
//        FakeStoreProductDto fakeStoreProduct = resp.getBody();
//
//        return convertFaKeStoreProductToProduct(fakeStoreProduct);
//
//    }

    @Override
    public Product getSingleProduct(Long id) throws ProductNotFoundException {
      //  throw new ProductNotFoundException("Something went wrong");
        throw new RuntimeException("Something went wrong");

    }

    @Override
    public List<Product> getAllProducts() {
        //ResponseEntity<List<FakeStoreProductDto>> resp = template.getForEntity("https://fakestoreapi.com/products/",List<FakeStoreProductDto>.class);
        ResponseEntity<FakeStoreProductDto[]> resp = template.getForEntity("https://fakestoreapi.com/products/",FakeStoreProductDto[].class);
        ArrayList<Product> list = new ArrayList<>();
        for(FakeStoreProductDto dto:resp.getBody())
        {

           list.add(convertFaKeStoreProductToProduct(dto));
        }
        return list;
    }

    @Override
    public Product createProduct(Product product) {
        return null;
    }

    @Override
    public boolean deleteProduct(Long productId) {
        return false;
    }

    public Product convertFaKeStoreProductToProduct(FakeStoreProductDto productDto)
    {
        Product product = new Product();
        Category category = new Category();
        category.setTitle(productDto.getCategory());
        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setImgUrl(productDto.getImage());
        product.setCategory(category);

        return product;
    }
}
