package uz.pdp.appproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appproject.entity.InputProduct;
import uz.pdp.appproject.payload.InputProductDto;
import uz.pdp.appproject.response.Apiresponse;
import uz.pdp.appproject.service.InputProductService;

@RestController
@RequestMapping("/api/inputproduct")
public class InputProductController {
    @Autowired
    InputProductService inputProductService;
    @GetMapping
    public Apiresponse all(){
        return inputProductService.getAll();
    }
    @GetMapping("/{id}")
    public Apiresponse one(@PathVariable Integer id){
        return inputProductService.getOne(id);
    }
    @PostMapping
    public Apiresponse add(@RequestBody InputProductDto dto){
        return inputProductService.addInputProduct(dto);

    }
    @PutMapping("/{id}")
    public Apiresponse edit(@PathVariable Integer id,@RequestBody InputProductDto dto){
        return inputProductService.editInputProduct(id, dto);
    }
    @DeleteMapping("/{id}")
    public Apiresponse delete(@PathVariable Integer id){
        return inputProductService.deleteInputProduct(id);
    }


}
