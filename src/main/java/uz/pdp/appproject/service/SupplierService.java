package uz.pdp.appproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appproject.entity.Supplier;
import uz.pdp.appproject.repository.SupplierRepository;
import uz.pdp.appproject.response.Apiresponse;

import java.util.List;

@Service
public class SupplierService {
    @Autowired
    SupplierRepository supplierRepository;
    public Apiresponse getAll(){
        List<Supplier> all = supplierRepository.findAll();
        return new Apiresponse("List of All supplies",true,all);
    }

    public Apiresponse addSupplier(Supplier mysupplier ){
       if (supplierRepository.existsByPhoneNumber(mysupplier.getPhoneNumber())){
            return new Apiresponse("Supplier with this phone number already exist",true);
        }
        Supplier supplier=new Supplier();
        supplier.setActive(mysupplier.getActive());
        supplier.setName(mysupplier.getName());
        supplier.setPhoneNumber(mysupplier.getPhoneNumber());
        Supplier save = supplierRepository.save(supplier);
        return new Apiresponse("Added successfully",true,save);

    }
    public Apiresponse editSupplier(Integer id,Supplier mysupplier){
        if (supplierRepository.existsById(id)) {
            Supplier supplier = supplierRepository.findById(id).get();
            supplier.setPhoneNumber(mysupplier.getPhoneNumber());
            supplier.setActive(mysupplier.getActive());
            supplier.setName(mysupplier.getName());
            Supplier save = supplierRepository.save(supplier);
            return new Apiresponse("Found and updated",true,save);
        }
        return new Apiresponse("Supplier with this id not found",false);
    }
    public Apiresponse deleteSupplier(Integer id){
        if (supplierRepository.existsById(id)) {
            supplierRepository.deleteById(id);
            return new Apiresponse("Found and deleted",true);
        }
        return new Apiresponse("Supplier with this id not found",false);
    }
    public Apiresponse getOne(Integer id){
        if (supplierRepository.existsById(id)) {
            Supplier supplier = supplierRepository.findById(id).get();
            return new Apiresponse("Supplier",true,supplier);
        }
        return new Apiresponse("Supplier with this id not found",false);

    }


}
