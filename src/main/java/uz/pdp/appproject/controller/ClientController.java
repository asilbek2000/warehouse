package uz.pdp.appproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appproject.entity.Client;
import uz.pdp.appproject.response.Apiresponse;
import uz.pdp.appproject.service.ClientService;

@RestController
@RequestMapping("/api/client")
public class ClientController {
    @Autowired

    ClientService clientService;
    @GetMapping
    public Apiresponse all(){
        return clientService.getAll();
    }

    @GetMapping("/{id}")
    public Apiresponse one(@PathVariable Integer id){
        return clientService.getOne(id);
    }

    @PostMapping
    public Apiresponse add(@RequestBody Client client){
        return clientService.addClient(client);
    }

    @PutMapping("/{id}")
    public Apiresponse edit(@PathVariable Integer id,@RequestBody Client client){
        return clientService.editClient(id, client);
    }
    @DeleteMapping("/{id}")
    public Apiresponse delete(@PathVariable Integer id){
        return clientService.deleteClient(id);
    }
}
