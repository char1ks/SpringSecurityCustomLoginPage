package com.example.CustomLoginFormSecurity.Service;

import com.example.CustomLoginFormSecurity.Model.Product;
import com.example.CustomLoginFormSecurity.Repository.productRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class productService {
    private productRepository operations;

    @Autowired
    public void setOperations(productRepository operations) {
        this.operations = operations;
    }

    public List<Product> getAllProducts(){
        return operations.findAll();
    }
    public Product getConcreteProduct(int id){
        return operations.findById(id).orElse(null);
    }
    public void saveProduct(Product product){
        operations.save(product);
    }
    public void updateProduct(int id,Product product){
        product.setId(id);
        operations.save(product);
    }
    public void deleteProduct(int id){
        operations.deleteById(id);
    }
}
