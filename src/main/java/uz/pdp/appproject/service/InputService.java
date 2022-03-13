package uz.pdp.appproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appproject.entity.Currency;
import uz.pdp.appproject.entity.Input;
import uz.pdp.appproject.entity.Supplier;
import uz.pdp.appproject.entity.Warehouse;
import uz.pdp.appproject.payload.InputDto;
import uz.pdp.appproject.repository.*;
import uz.pdp.appproject.response.Apiresponse;

import java.util.List;

@Service
public class InputService {
    @Autowired
    InputRepository inputRepository;
    @Autowired
    CurrencyRepository currencyRepository;
    @Autowired
    WarehouseRepository warehouseRepository;
    @Autowired
    SupplierRepository supplierRepository;
    public Apiresponse getAll(){
        List<Input> all = inputRepository.findAll();
        return new Apiresponse("List of Inputs",true,all);
    }
            public Apiresponse addInput(InputDto dto) {
                if (supplierRepository.existsById(dto.getSupplierId())
                        && currencyRepository.existsById(dto.getCurrencyId())
                        && warehouseRepository.existsById(dto.getWarehouseId())
                ) {
                    Supplier supplier = supplierRepository.findById(dto.getSupplierId()).get();
                    Currency currency = currencyRepository.findById(dto.getCurrencyId()).get();
                    Warehouse warehouse = warehouseRepository.findById(dto.getWarehouseId()).get();
                    Input input = new Input();
                    input.setCode(dto.getCode());
                    input.setDate(dto.getDate());
                    input.setFactureNumber(dto.getFactureNumber());
                    input.setWarehouse(warehouse);
                    input.setSupplier(supplier);
                    input.setCurrency(currency);
                    Input save = inputRepository.save(input);
                    return new Apiresponse("Added successfully", true, save);
                }
                return new Apiresponse("Something wrong  with entered parametres ",false);
            }

            public Apiresponse editInput(Integer id,InputDto dto){
                if (inputRepository.existsById(id)) {
                    if (supplierRepository.existsById(dto.getSupplierId())
                            && currencyRepository.existsById(dto.getCurrencyId())
                            && warehouseRepository.existsById(dto.getWarehouseId())
                    ){
                        Supplier supplier = supplierRepository.findById(dto.getSupplierId()).get();
                        Currency currency = currencyRepository.findById(dto.getCurrencyId()).get();
                        Warehouse warehouse = warehouseRepository.findById(dto.getWarehouseId()).get();
                        Input input = inputRepository.findById(id).get();
                        input.setCurrency(currency);
                        input.setWarehouse(warehouse);
                        input.setSupplier(supplier);
                        input.setCode(dto.getCode());
                        input.setDate(dto.getDate());
                        input.setFactureNumber(dto.getFactureNumber());
                        Input save = inputRepository.save(input);
                        return new Apiresponse("Found and updated",true,save);
                    }
                    return new Apiresponse("Something wrong with entered parametres",false);
                }
                return new Apiresponse("Input not found with this id",false);
            }


            public Apiresponse deleteInput(Integer id){
                if (inputRepository.existsById(id)) {
                    inputRepository.deleteById(id);
                    return new Apiresponse("Found and deleted",true);
                }
                return new Apiresponse("Input not found with this id",false);
            }
            public Apiresponse getOne(Integer id){
                if (inputRepository.existsById(id)) {
                    Input input = inputRepository.findById(id).get();
                    return new Apiresponse("Input",true,input);
                }
                return new Apiresponse("Input not found with this id",false);

            }

}
