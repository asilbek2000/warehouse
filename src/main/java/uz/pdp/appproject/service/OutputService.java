package uz.pdp.appproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appproject.entity.Client;
import uz.pdp.appproject.entity.Currency;
import uz.pdp.appproject.entity.Output;
import uz.pdp.appproject.entity.Warehouse;
import uz.pdp.appproject.payload.OutputDto;
import uz.pdp.appproject.repository.*;
import uz.pdp.appproject.response.Apiresponse;

import java.util.List;

@Service
public class OutputService {
    @Autowired
    OutputRepository outputRepository;
    @Autowired
    WarehouseRepository warehouseRepository;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    CurrencyRepository currencyRepository;
    public Apiresponse getAll(){
        List<Output> all = outputRepository.findAll();
        return new Apiresponse("List of Outputs",true,all);

    }
    public Apiresponse addOutput(OutputDto dto){
        if(clientRepository.existsById(dto.getClientId())
        && warehouseRepository.existsById(dto.getWarehouseId())
                && currencyRepository.existsById(dto.getCurrencyId())

        ){
            Client client = clientRepository.findById(dto.getClientId()).get();
            Warehouse warehouse = warehouseRepository.findById(dto.getWarehouseId()).get();
            Currency currency = currencyRepository.findById(dto.getCurrencyId()).get();
            Output output=new Output();
            output.setClient(client);
            output.setCurrency(currency);
            output.setWarehouse(warehouse);
            output.setCode(dto.getCode());
            output.setDate(dto.getDate());
            output.setFactureNumber(dto.getFactureNumber());
            Output save = outputRepository.save(output);
            return new Apiresponse("Added successfully",true,save);
        }
        return new Apiresponse("Something wrong with entered parametres",false);

    }
    public Apiresponse editOutput(Integer id,OutputDto dto){
        if (outputRepository.existsById(id)) {
            if (warehouseRepository.existsById(dto.getWarehouseId())
            && currencyRepository.existsById(dto.getCurrencyId())&&
                    clientRepository.existsById(dto.getClientId())

            ) {
                Warehouse warehouse = warehouseRepository.findById(dto.getWarehouseId()).get();
                Client client = clientRepository.findById(dto.getClientId()).get();
                Currency currency = currencyRepository.findById(dto.getCurrencyId()).get();

                Output output = outputRepository.findById(id).get();
                output.setWarehouse(warehouse);
                output.setClient(client);
                output.setCurrency(currency);
                output.setCode(dto.getCode());
                output.setDate(dto.getDate());
                output.setFactureNumber(dto.getFactureNumber());
                Output save = outputRepository.save(output);
                return new Apiresponse("Found and updated",true,save);

            }
            return new Apiresponse("Something wrong with entered parametrs",false);

        }
        return new Apiresponse("Output with this id not found",false);
    }
    public Apiresponse deleteOutput(Integer id){
        if (outputRepository.existsById(id)) {
            outputRepository.deleteById(id);
            return new Apiresponse("Found and deleted",true);
        }
        return new Apiresponse("Output with this id not found",false);
    }
    public Apiresponse getOne(Integer id){
        if (outputRepository.existsById(id)) {
            Output output = outputRepository.findById(id).get();
            return new Apiresponse("Output",true,output);
        }
        return new Apiresponse("Output with this id not found",false);

    }

}
