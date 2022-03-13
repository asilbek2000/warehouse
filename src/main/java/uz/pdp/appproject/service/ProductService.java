package uz.pdp.appproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appproject.entity.Attachment;
import uz.pdp.appproject.entity.Category;
import uz.pdp.appproject.entity.Measurment;
import uz.pdp.appproject.entity.Product;
import uz.pdp.appproject.payload.ProductDto;
import uz.pdp.appproject.repository.*;
import uz.pdp.appproject.response.Apiresponse;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    MeasurmentRepository measurmentRepository;


    public Apiresponse getAll(){
        List<Product> all = productRepository.findAll();
        return new Apiresponse("List of Products",true,all);
    }
    public Apiresponse addProduct(ProductDto dto){
        if (productRepository.existsByName(dto.getName())) {
            return new Apiresponse("Product with this name already exists",false);

        }
        if (categoryRepository.existsById(dto.getCategoryId()) &&
           attachmentRepository.existsById(dto.getPhotoId()) &&
                measurmentRepository.existsById(dto.getMeasurmentid())
        ) {
            Category category = categoryRepository.findById(dto.getCategoryId()).get();
            Attachment attachment = attachmentRepository.findById(dto.getPhotoId()).get();
            Measurment measurment = measurmentRepository.findById(dto.getMeasurmentid()).get();

            Product product = new Product();
            product.setActive(dto.getActive());
            product.setName(dto.getName());
            product.setCategory(category);
            product.setPhoto(attachment);
            product.setMeasurment(measurment);
            Product save = productRepository.save(product);
            return new Apiresponse("Added successfully", true, save);
        }
        return new Apiresponse("Error ",false);
    }
    public Apiresponse editProduct(Integer id,ProductDto dto){
        if (productRepository.existsById(id)) {
            Category category = categoryRepository.findById(dto.getCategoryId()).get();
            Attachment attachment = attachmentRepository.findById(dto.getPhotoId()).get();
            Measurment measurment = measurmentRepository.findById(dto.getMeasurmentid()).get();
            Product product = productRepository.findById(id).get();
            product.setName(dto.getName());
            product.setActive(dto.getActive());
            product.setMeasurment(measurment);
            product.setCategory(category);
            product.setPhoto(attachment);
            Product save = productRepository.save(product);
            return new Apiresponse("Found and updated",true,save);
        }
        return new Apiresponse("Product not found with this id",false);
    }
    public Apiresponse deleteProduct(Integer id){
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return new Apiresponse("Found and deleted",true);
        }
        return new Apiresponse("Product with this id not found",false);
    }
    public Apiresponse getOne(Integer id){
        if (productRepository.existsById(id)) {
            Product product = productRepository.findById(id).get();
            return new Apiresponse("Product",true,product);
        }
        return new Apiresponse("Product with this id not found",false);
    }
}
