package uz.pdp.appproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appproject.payload.InputDto;
import uz.pdp.appproject.response.Apiresponse;
import uz.pdp.appproject.service.InputService;

@RestController
@RequestMapping("/api/input")
public class InputController {
    @Autowired
    InputService inputService;
    @GetMapping
    public Apiresponse all(){
        return inputService.getAll();
    }
    @GetMapping("/{id}")
    public Apiresponse one(@PathVariable Integer id){
        return inputService.getOne(id);
    }
    @PostMapping
    public Apiresponse add(@RequestBody InputDto dto){
        return inputService.addInput(dto);
    }

    @PutMapping("/{id}")
    public Apiresponse edit(@PathVariable Integer id,@RequestBody InputDto dto){
        return inputService.editInput(id, dto);
    }
    @DeleteMapping("/{id}")
    public Apiresponse delete(@PathVariable Integer id){
        return inputService.deleteInput(id);
    }










}
