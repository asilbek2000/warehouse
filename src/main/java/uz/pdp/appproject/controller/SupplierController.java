package uz.pdp.appproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appproject.entity.Measurment;
import uz.pdp.appproject.entity.Supplier;
import uz.pdp.appproject.response.Apiresponse;
import uz.pdp.appproject.service.MeasurmentService;
import uz.pdp.appproject.service.SupplierService;

@RestController
@RequestMapping("/api/supplier")
public class SupplierController {
     @Autowired
     SupplierService supplierService;
    @GetMapping
    public Apiresponse all(){
        return supplierService.getAll();
    }
    @GetMapping("/{id}")
    public Apiresponse one(@PathVariable Integer id){
        return supplierService.getOne(id);
    }


    @PostMapping
    public Apiresponse add(@RequestBody Supplier supplier){
        return supplierService.addSupplier(supplier);
    }
    @PutMapping("/{id}")
    public Apiresponse edit(@PathVariable Integer id,@RequestBody Supplier supplier){
        return supplierService.editSupplier(id,supplier );
    }
    @DeleteMapping("/{id}")
     public Apiresponse delete(@PathVariable Integer id){
        return supplierService.deleteSupplier(id);
    }

}

