package uz.pdp.appproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appproject.entity.Category;
import uz.pdp.appproject.payload.CategoryDto;
import uz.pdp.appproject.response.Apiresponse;
import uz.pdp.appproject.service.CategoryService;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
     @Autowired
    CategoryService categoryService;
    @GetMapping
    public Apiresponse all(){
        return categoryService.getAll();
    }
    @GetMapping("/{id}")
    public Apiresponse one(@PathVariable Integer id){
        return categoryService.getOne(id);
    }


    @PostMapping
    public Apiresponse add(@RequestBody CategoryDto dto){
        return categoryService.addCategory(dto);
    }
    @PutMapping("/{id}")
    public Apiresponse edit(@PathVariable Integer id,@RequestBody CategoryDto dto){
        return categoryService.editCategory(id, dto);
    }
    @DeleteMapping("/{id}")
     public Apiresponse delete(@PathVariable Integer id){
        return categoryService.deleteCategory(id);
    }

}

