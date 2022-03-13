package uz.pdp.appproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appproject.entity.Measurment;
import uz.pdp.appproject.payload.CategoryDto;
import uz.pdp.appproject.response.Apiresponse;
import uz.pdp.appproject.service.CategoryService;
import uz.pdp.appproject.service.MeasurmentService;

@RestController
@RequestMapping("/api/measurment")
public class MeasurmentController {
     @Autowired
     MeasurmentService  measurmentService;
    @GetMapping
    public Apiresponse all(){
        return measurmentService.getAll();
    }
    @GetMapping("/{id}")
    public Apiresponse one(@PathVariable Integer id){
        return measurmentService.getOne(id);
    }


    @PostMapping
    public Apiresponse add(@RequestBody Measurment measurment){
        return measurmentService.addMeasurment(measurment);
    }
    @PutMapping("/{id}")
    public Apiresponse edit(@PathVariable Integer id,@RequestBody Measurment measurment){
        return measurmentService.editMeasurment(id, measurment);
    }
    @DeleteMapping("/{id}")
     public Apiresponse delete(@PathVariable Integer id){
        return measurmentService.deleteMeasurment(id);
    }

}

