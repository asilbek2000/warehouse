package uz.pdp.appproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appproject.entity.Currency;
import uz.pdp.appproject.response.Apiresponse;
import uz.pdp.appproject.service.CurrencyService;

@RestController
@RequestMapping("/api/currency")
public class CurrencyController {
    @Autowired
    CurrencyService currencyService;

    @GetMapping
    public Apiresponse all(){
        return currencyService.getAll();
    }

    @GetMapping("/{id}")
    public Apiresponse one(@PathVariable Integer id){
        return currencyService.getOne(id);
    }

    @PostMapping
    public Apiresponse add(@RequestBody Currency currency){
        return currencyService.addCurrency(currency);

    }

    @PutMapping("/{id}")
    public Apiresponse edit(@PathVariable Integer id,@RequestBody Currency currency){
        return currencyService.editCurrency(id, currency);
    }
    @DeleteMapping("/{id}")
    public Apiresponse delete(@PathVariable Integer id){
        return currencyService.deleteCurrency(id);
    }













}
