package com.rajumajhi.ecom.controller;

import com.rajumajhi.ecom.controller.model.Products;
import com.rajumajhi.ecom.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class ProductController{
    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public List<Products> getProducts(){


//        System.out.println(productService.getAllProducts());

        return productService.getAllProducts();
    }
    @PostMapping("/product")
    public ResponseEntity<?> addProduct(@RequestPart  Products product, @RequestPart MultipartFile imageFile){
        Products saveProduct= null;
        try {
            saveProduct = productService.addorUpdateProduct(product,imageFile);

            return new ResponseEntity<>(saveProduct, HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }





    }
    @PutMapping("/product/{Id}")
    public  ResponseEntity<String> updateProduct(@PathVariable int id, @RequestPart Products product,@RequestPart MultipartFile imageFile){
        Products updatedProduct=null;

        try {
            updatedProduct=productService.addorUpdateProduct(product,imageFile);
            return new ResponseEntity<>("updated!",HttpStatus.ACCEPTED);
        }
        catch (IOException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }


    }
    @DeleteMapping("/product/{productId}")
    public ResponseEntity<String>  deleteProduct(@PathVariable int productId){

        Products product = productService.searchProductById(productId);
        if(product!=null){
            productService.deleteProduct(productId);
            return new ResponseEntity<>("deleted!",HttpStatus.OK);



        }
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);




    }
    @GetMapping("/product/{searchProductById}")
    public ResponseEntity<Products> searchProduct(@PathVariable int searchProductById){

        Products productsById=productService.searchProductById(searchProductById);

        if(productsById.getId() > 0)
            return new ResponseEntity<>(productsById,HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);




    }
    @GetMapping("/product/{productId}/image")
    public ResponseEntity<byte[]> fetchImage(@PathVariable int productId){

        Products product =productService.searchProductById(productId);

        if(product.getId() > 0)
            return new ResponseEntity<>(product.getImageData(),HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);


    }

//    @GetMapping("/products/search")
//    public ResponseEntity<List<Products>> searchProduct(@RequestBody String keyword){
//
//        List<Products> products = productService.searchproduct(keyword);
//        System.out.println("Searching with : "+keyword);
//
//        return new ResponseEntity<>(products,HttpStatus.OK);
//
//    }


}
