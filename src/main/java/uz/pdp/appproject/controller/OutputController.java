package uz.pdp.appproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appproject.payload.CategoryDto;
import uz.pdp.appproject.payload.OutputDto;
import uz.pdp.appproject.response.Apiresponse;
import uz.pdp.appproject.service.CategoryService;
import uz.pdp.appproject.service.OutputService;

@RestController
@RequestMapping("/api/output")
public class OutputController {
     @Autowired
     OutputService outputService;
    @GetMapping
    public Apiresponse all(){
        return outputService.getAll();
    }
    @GetMapping("/{id}")
    public Apiresponse one(@PathVariable Integer id){
        return outputService.getOne(id);
    }


    @PostMapping
    public Apiresponse add(@RequestBody OutputDto dto){
        return outputService.addOutput(dto);
    }
    @PutMapping("/{id}")
    public Apiresponse edit(@PathVariable Integer id,@RequestBody OutputDto dto){
        return outputService.editOutput(id, dto);
    }
    @DeleteMapping("/{id}")
     public Apiresponse delete(@PathVariable Integer id){
        return outputService.deleteOutput(id);
    }

}

