package uz.pdp.appproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appproject.entity.Output;
import uz.pdp.appproject.entity.OutputProduct;
import uz.pdp.appproject.entity.Product;
import uz.pdp.appproject.payload.OutputProductDto;
import uz.pdp.appproject.repository.OutputProductRepository;
import uz.pdp.appproject.repository.OutputRepository;
import uz.pdp.appproject.repository.ProductRepository;
import uz.pdp.appproject.response.Apiresponse;

import java.util.List;

@Service
public class OutputProductService {
    @Autowired
    OutputProductRepository outputProductRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    OutputRepository outputRepository;
    public Apiresponse getAll(){
        List<OutputProduct> all = outputProductRepository.findAll();
        return new Apiresponse("List of outputProducts",true,all);
    }


    public Apiresponse addOutputProduct(OutputProductDto dto){
        if (outputRepository.existsById(dto.getOutputid())
        && productRepository.existsById(dto.getProductId())
        ){
            Output output = outputRepository.findById(dto.getOutputid()).get();
            Product product = productRepository.findById(dto.getProductId()).get();
            OutputProduct outputProduct=new OutputProduct();
            outputProduct.setOutput(output);
            outputProduct.setProduct(product);
            outputProduct.setAmount(dto.getAmount());
            outputProduct.setPrice(dto.getPrice());
            outputProduct.setExpiredate(dto.getExpiredate());
            OutputProduct save = outputProductRepository.save(outputProduct);
            return new Apiresponse("Added successfully",true,save);
        }
        return new Apiresponse("Something went wrong with entered parametres",false);

    }
    public Apiresponse editOutputProduct(Integer id,OutputProductDto dto){
        if (outputProductRepository.existsById(id)) {
            if (outputRepository.existsById(dto.getOutputid())
                    && productRepository.existsById(dto.getProductId())
            ){
                Output output = outputRepository.findById(dto.getOutputid()).get();
                Product product = productRepository.findById(dto.getProductId()).get();
                OutputProduct outputProduct = outputProductRepository.findById(id).get();
                outputProduct.setProduct(product);
                outputProduct.setOutput(output);
                outputProduct.setExpiredate(dto.getExpiredate());
                outputProduct.setPrice(dto.getPrice());
                outputProduct.setAmount(dto.getAmount());
                OutputProduct save = outputProductRepository.save(outputProduct);
                return new Apiresponse("Found and updated",true,save);
            }
            return new Apiresponse("Something went wrong with entered parametres",false);
        }
        return new Apiresponse("OutputProduct with this id not found",false);
    }
    public Apiresponse deleteOutputProduct(Integer id){
        if (outputProductRepository.existsById(id)) {
            outputProductRepository.deleteById(id);
            return new Apiresponse("Found and deleted",true);
        }
        return new Apiresponse("OutputProduct with this id not found",false);

    }
    public Apiresponse getOne(Integer id){
        if (outputProductRepository.existsById(id)) {
            OutputProduct outputProduct = outputProductRepository.findById(id).get();
            return new Apiresponse("OutputProduct",true,outputProduct);
        }
        return new Apiresponse("OutputProduct with this id not found",false);

    }
}
