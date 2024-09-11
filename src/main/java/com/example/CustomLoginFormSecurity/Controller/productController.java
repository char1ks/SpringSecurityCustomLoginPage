package com.example.CustomLoginFormSecurity.Controller;

import com.example.CustomLoginFormSecurity.Model.Product;
import com.example.CustomLoginFormSecurity.Service.productService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
@Controller
@RequestMapping("/product")
public class productController {
    private productService operations;

    @Autowired
    public void setOperations(productService operations) {
        this.operations = operations;
    }

    //ELEMENTS
    @GetMapping
    public String mainPage(Model model){
        model.addAttribute("allProducts",operations.getAllProducts());
        return "product/mainPage";
    }
    @GetMapping("/{id}")
    public String concretePage(@PathVariable("id")int id,
                               Model model){
        model.addAttribute("concreteProduct",operations.getConcreteProduct(id));
        return "product/concretePage";
    }
    //ADD
    @GetMapping("/add")
    public String addPage(@ModelAttribute("newProduct")Product product){
        return "product/newPage";
    }
    @PostMapping
    public String addInDB(@ModelAttribute("newProduct") @Valid Product product,
                          BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "product/newPage";
        operations.saveProduct(product);
        return "redirect:/product";
    }
    //EDIT
    @GetMapping("/{id}/edit")
    public String editPage(@PathVariable("id")int id,Model model){
        model.addAttribute("editProduct",operations.getConcreteProduct(id));
        model.addAttribute("productId",id);
        return "product/editPage";
    }
    @PatchMapping("/{id}")
    public String editInDB(@PathVariable("id")int id,
                           @ModelAttribute("editProduct") @Valid Product product,
                           BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "product/editPage";
        operations.updateProduct(id,product);
        return "redirect:/product";
    }
    //DELETE
    @DeleteMapping("/{id}")
    public String deleteInDB(@PathVariable("id")int id){
        operations.deleteProduct(id);
        return "redirect:/product";
    }
}
