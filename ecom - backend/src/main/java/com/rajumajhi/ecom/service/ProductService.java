package com.rajumajhi.ecom.service;

import com.rajumajhi.ecom.controller.model.Products;
import com.rajumajhi.ecom.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepo productRepo;


    public List<Products> getAllProducts() {


//        System.out.println("Product Service Data:  "+productRepo.findAll());

        return productRepo.findAll();
    }







    public void deleteProduct(int productId) {

        productRepo.deleteById(productId);
    }

    public Products searchProductById(int searchProductById) {

      return   productRepo.findById(searchProductById).orElse(new Products());
    }

    public Products addorUpdateProduct(Products product, MultipartFile imageFile) throws IOException {


        product.setImageName(imageFile.getOriginalFilename());
        product.setImageType(imageFile.getContentType());
        product.setImageData(imageFile.getBytes());
        return productRepo.save(product);
    }

//    public List<Products> searchproduct(String keyword) {
//
//        return productRepo.searchProduct(keyword);
//    }

//    public Products updateProduct(Products product, MultipartFile imageFile) throws IOException {
//
//        product.setImageName(imageFile.getOriginalFilename());
//        product.setImageType(imageFile.getContentType());
//        product.setImageData(imageFile.getBytes());
//        return productRepo.save(product);
//    }
}
