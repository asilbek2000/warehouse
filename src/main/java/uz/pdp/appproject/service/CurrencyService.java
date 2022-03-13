package uz.pdp.appproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appproject.entity.Currency;
import uz.pdp.appproject.repository.CurrencyRepository;
import uz.pdp.appproject.response.Apiresponse;

import java.util.List;

@Service
public class CurrencyService {
    @Autowired
    CurrencyRepository currencyRepository;
    public Apiresponse getAll(){
        List<Currency> all = currencyRepository.findAll();
        return new Apiresponse("List of currencies",true,all);
    }
    public Apiresponse addCurrency(Currency mycurrency){
        if (currencyRepository.existsByName(mycurrency.getName())) {
            return new Apiresponse("Currency with this name already exists",false);
        }
        Currency currency=new Currency();
        currency.setActive(mycurrency.getActive());
        currency.setName(mycurrency.getName());
        Currency save = currencyRepository.save(currency);
        return new Apiresponse("Added successfully",true,save);
    }
    public Apiresponse editCurrency(Integer id,Currency mycurrency){
        if (currencyRepository.existsById(id)) {
            Currency currency = currencyRepository.findById(id).get();
            currency.setName(mycurrency.getName());
            currency.setActive(mycurrency.getActive());
            Currency save = currencyRepository.save(currency);
            return new Apiresponse("Foudn and updated",true,save);
        }
        return new Apiresponse("Currency with this id does not exist",false);
    }
    public Apiresponse deleteCurrency(Integer id){
        if (currencyRepository.existsById(id)) {
            currencyRepository.deleteById(id);
            return new Apiresponse("Found and deleted",true);
        }
        return new Apiresponse("Currency with this id not found",false);
    }
    public Apiresponse getOne(Integer id){
        if (currencyRepository.existsById(id)) {
            Currency currency = currencyRepository.findById(id).get();
            return new Apiresponse("Currency",true,currency);
        }
        return new Apiresponse("Currency with this id not found",false);
    }
}
