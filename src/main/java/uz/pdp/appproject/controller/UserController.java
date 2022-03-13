package uz.pdp.appproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appproject.payload.CategoryDto;
import uz.pdp.appproject.payload.UserDto;
import uz.pdp.appproject.response.Apiresponse;
import uz.pdp.appproject.service.CategoryService;
import uz.pdp.appproject.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
     @Autowired
     UserService userService;
    @GetMapping
    public Apiresponse all(){
        return userService.getAll();
    }
    @GetMapping("/{id}")
    public Apiresponse one(@PathVariable Integer id){
        return userService.getOne(id);
    }


    @PostMapping
    public Apiresponse add(@RequestBody UserDto dto){
        return userService.addUser(dto);
    }
    @PutMapping("/{id}")
    public Apiresponse edit(@PathVariable Integer id,@RequestBody UserDto dto){
        return userService.editUser(id, dto);
    }
    @DeleteMapping("/{id}")
     public Apiresponse delete(@PathVariable Integer id){
        return userService.deleteUser(id);
    }

}

