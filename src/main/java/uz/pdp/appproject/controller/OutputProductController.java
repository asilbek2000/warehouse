package uz.pdp.appproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appproject.entity.OutputProduct;
import uz.pdp.appproject.payload.OutputDto;
import uz.pdp.appproject.payload.OutputProductDto;
import uz.pdp.appproject.response.Apiresponse;
import uz.pdp.appproject.service.OutputProductService;
import uz.pdp.appproject.service.OutputService;

@RestController
@RequestMapping("/api/outputproduct")
public class OutputProductController {
     @Autowired
     OutputProductService outputProductService;
    @GetMapping
    public Apiresponse all(){
        return outputProductService.getAll();
    }
    @GetMapping("/{id}")
    public Apiresponse one(@PathVariable Integer id){
        return outputProductService.getOne(id);
    }


    @PostMapping
    public Apiresponse add(@RequestBody OutputProductDto dto){
        return outputProductService.addOutputProduct(dto);
    }
    @PutMapping("/{id}")
    public Apiresponse edit(@PathVariable Integer id,@RequestBody OutputProductDto dto){
        return outputProductService.editOutputProduct(id, dto);
    }
    @DeleteMapping("/{id}")
     public Apiresponse delete(@PathVariable Integer id){
        return outputProductService.deleteOutputProduct(id);
    }

}

