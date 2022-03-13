package uz.pdp.appproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appproject.entity.Warehouse;
import uz.pdp.appproject.payload.CategoryDto;
import uz.pdp.appproject.response.Apiresponse;
import uz.pdp.appproject.service.CategoryService;
import uz.pdp.appproject.service.WarehouseService;

@RestController
@RequestMapping("/api/warehouse")
public class WarehouseController {
     @Autowired
     WarehouseService warehouseService;
    @GetMapping
    public Apiresponse all(){
        return warehouseService.getAll();
    }
    @GetMapping("/{id}")
    public Apiresponse one(@PathVariable Integer id){
        return warehouseService.getOne(id);
    }


    @PostMapping
    public Apiresponse add(@RequestBody Warehouse warehouse){
        return warehouseService.addWarehouse(warehouse);
    }
    @PutMapping("/{id}")
    public Apiresponse edit(@PathVariable Integer id,@RequestBody Warehouse warehouse){
        return warehouseService.editWarehouse(id, warehouse);
    }
    @DeleteMapping("/{id}")
     public Apiresponse delete(@PathVariable Integer id){
        return warehouseService.deleteWarehouse(id);
    }

}

