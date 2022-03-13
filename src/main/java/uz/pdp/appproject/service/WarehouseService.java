package uz.pdp.appproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appproject.entity.Warehouse;
import uz.pdp.appproject.repository.WarehouseRepository;
import uz.pdp.appproject.response.Apiresponse;

import java.util.List;

@Service
public class WarehouseService {
    @Autowired
    WarehouseRepository warehouseRepository;
    public Apiresponse getAll(){
        List<Warehouse> all = warehouseRepository.findAll();
        return new Apiresponse("List of all warehouses",true,all);
    }
    public Apiresponse addWarehouse(Warehouse mywarehouse){
        if (warehouseRepository.existsByName(mywarehouse.getName())) {
            return new Apiresponse("Warehouse with this name already exist",false);
        }
        Warehouse warehouse=new Warehouse();
        warehouse.setActive(mywarehouse.getActive());
        warehouse.setName(mywarehouse.getName());
        Warehouse save = warehouseRepository.save(warehouse);
        return new Apiresponse("Added successfully",true,save);
    }


    public Apiresponse editWarehouse(Integer id,Warehouse mywarehouse){
        if (warehouseRepository.existsById(id)) {
            Warehouse warehouse = warehouseRepository.findById(id).get();
            warehouse.setName(mywarehouse.getName());
            warehouse.setActive(mywarehouse.getActive());
            Warehouse save = warehouseRepository.save(warehouse);
            return new Apiresponse("Found and updated",true,save);
        }
        return new Apiresponse("Warehouse with this id not found",false);
    }
    public Apiresponse deleteWarehouse(Integer id){
        if (warehouseRepository.existsById(id)) {
            warehouseRepository.deleteById(id);
            return new Apiresponse("Found and deleted",true);
        }
        return new Apiresponse("Warehouse with this id not found",false);
    }
    public Apiresponse getOne(Integer id){
        if (warehouseRepository.existsById(id)) {
            Warehouse warehouse = warehouseRepository.findById(id).get();
            return new Apiresponse("Warehouse",true,warehouse);
        }
        return new Apiresponse("Warehouse with this id not found",false);
    }
}
