package uz.pdp.appproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appproject.entity.Category;
import uz.pdp.appproject.payload.CategoryDto;
import uz.pdp.appproject.repository.CategoryRepository;
import uz.pdp.appproject.response.Apiresponse;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    public Apiresponse getAll(){
        List<Category> all = categoryRepository.findAll();
        return new Apiresponse("List of categories",true,all);
    }
    public Apiresponse addCategory(CategoryDto dto){
        if (!categoryRepository.existsByName(dto.getName())) {
            Category parentcategory = categoryRepository.findById(dto.getParentCategoryId()).get();
            Category category=new Category();
            category.setParentCategory(parentcategory);
            category.setActive(dto.getActive());
            category.setName(dto.getName());
            Category save = categoryRepository.save(category);
            return new Apiresponse("Added successfully",true,save);

        }
        return new Apiresponse("Category with Such id already exist",false);
    }
    public Apiresponse editCategory(Integer id,CategoryDto dto){
        if (categoryRepository.existsById(id)) {
            Category parentcategory = categoryRepository.findById(dto.getParentCategoryId()).get();
            Category category = categoryRepository.findById(id).get();
            category.setName(dto.getName());
            category.setActive(dto.getActive());
            category.setParentCategory(parentcategory);
            Category save = categoryRepository.save(category);
            return new Apiresponse("Found and updated",true,save);

        }
        return new Apiresponse("Category with such id does not exist",false);
    }
    public Apiresponse deleteCategory(Integer id){
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
            return new Apiresponse("Found and deleted",true);
        }
        return new Apiresponse("Category with this id does not exist",false);

    }
    public Apiresponse getOne(Integer id){
        if (categoryRepository.existsById(id)) {
            Category category = categoryRepository.findById(id).get();
            return new Apiresponse("Category",true,category);
        }
        return new Apiresponse("Category with this id does not exist",false);
    }
}
