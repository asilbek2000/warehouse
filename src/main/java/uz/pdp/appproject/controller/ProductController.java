package uz.pdp.appproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appproject.entity.Measurment;
import uz.pdp.appproject.entity.Product;
import uz.pdp.appproject.payload.ProductDto;
import uz.pdp.appproject.response.Apiresponse;
import uz.pdp.appproject.service.MeasurmentService;
import uz.pdp.appproject.service.ProductService;

@RestController
@RequestMapping("/api/product")
public class ProductController {
     @Autowired
     ProductService productService;
    @GetMapping
    public Apiresponse all(){
        return productService.getAll();
    }
    @GetMapping("/{id}")
    public Apiresponse one(@PathVariable Integer id){
        return productService.getOne(id);
    }


    @PostMapping
    public Apiresponse add(@RequestBody ProductDto dto){
        return productService.addProduct(dto);
    }
    @PutMapping("/{id}")
    public Apiresponse edit(@PathVariable Integer id,@RequestBody ProductDto dto){
        return productService.editProduct(id, dto);
    }
    @DeleteMapping("/{id}")
     public Apiresponse delete(@PathVariable Integer id){
        return productService.deleteProduct(id);
    }

}

