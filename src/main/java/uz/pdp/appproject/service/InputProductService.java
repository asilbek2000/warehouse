package uz.pdp.appproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appproject.entity.Input;
import uz.pdp.appproject.entity.InputProduct;
import uz.pdp.appproject.entity.Product;
import uz.pdp.appproject.payload.InputProductDto;
import uz.pdp.appproject.repository.InputProductRepository;
import uz.pdp.appproject.repository.InputRepository;
import uz.pdp.appproject.repository.ProductRepository;
import uz.pdp.appproject.response.Apiresponse;

import java.util.List;

@Service
public class InputProductService {
    @Autowired
    InputProductRepository inputProductRepository;
    @Autowired
    InputRepository inputRepository;
    @Autowired
    ProductRepository productRepository;
    public Apiresponse getAll(){
        List<InputProduct> all = inputProductRepository.findAll();
        return new Apiresponse("List of InputProduct",true,all);
    }

    public Apiresponse addInputProduct(InputProductDto dto) {
        if (productRepository.existsById(dto.getProductId())
                && inputRepository.existsById(dto.getInputId())) {
            Product product = productRepository.findById(dto.getProductId()).get();
            Input input = inputRepository.findById(dto.getInputId()).get();
            InputProduct inputProduct = new InputProduct();
            inputProduct.setProduct(product);
            inputProduct.setInput(input);
            inputProduct.setAmount(dto.getAmount());
            inputProduct.setPrice(dto.getPrice());
            inputProduct.setExpiredate(dto.getExpiredate());
            InputProduct save = inputProductRepository.save(inputProduct);
            return new Apiresponse("Added successfully",true,save);

        }
        return new Apiresponse("Something wrong with entered parametres",false);
    }
    public Apiresponse editInputProduct(Integer id,InputProductDto dto){
        if (inputProductRepository.existsById(id)) {
            if (productRepository.existsById(dto.getProductId())
                    && inputRepository.existsById(dto.getInputId())){
                Product product = productRepository.findById(dto.getProductId()).get();
                Input input = inputRepository.findById(dto.getInputId()).get();
                InputProduct inputProduct = inputProductRepository.findById(id).get();
                inputProduct.setProduct(product);
                inputProduct.setInput(input);
                inputProduct.setExpiredate(dto.getExpiredate());
                inputProduct.setPrice(dto.getPrice());
                inputProduct.setAmount(dto.getAmount());
                InputProduct save = inputProductRepository.save(inputProduct);
                return new Apiresponse("Found and updated",true,save);

            }
            return new Apiresponse("Something wrong with entered parametres",false);
        }
        return new Apiresponse("InputProduct with this id not found",false);
    }
    public Apiresponse deleteInputProduct(Integer id){
        if (inputProductRepository.existsById(id)) {

            inputProductRepository.deleteById(id);
            return new Apiresponse("Found and deleted",true);
        }
        return new Apiresponse("InputProduct with this id not found",false);
    }
    public Apiresponse getOne(Integer id){
        if (inputProductRepository.existsById(id)) {
            InputProduct inputProduct = inputProductRepository.findById(id).get();
            return new Apiresponse("InputProduct",true,inputProduct);
        }
        return new Apiresponse("InputProduct with this id not found",false);
    }
}
