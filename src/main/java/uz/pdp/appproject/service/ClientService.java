package uz.pdp.appproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appproject.entity.Client;
import uz.pdp.appproject.repository.ClientRepository;
import uz.pdp.appproject.response.Apiresponse;

import java.util.List;

@Service
public class ClientService {
    @Autowired
    ClientRepository clientRepository;
    public Apiresponse getAll(){
        List<Client> all = clientRepository.findAll();
        return new Apiresponse("List of Clients",true,all);
    }
    public Apiresponse addClient(Client myclient){
        if (!clientRepository.existsByPhoneNumber(myclient.getPhoneNumber())) {
            Client client=new Client();
            client.setName(myclient.getName());
            client.setPhoneNumber(myclient.getPhoneNumber());
            Client save = clientRepository.save(client);
            return new Apiresponse("Added successfully",true,save);
        }
        return new Apiresponse("Client with this phoneNumber alreaady exist",true);
    }
    public Apiresponse editClient(Integer id,Client myclient){
        if (clientRepository.existsById(id)) {
            Client client = clientRepository.findById(id).get();
            client.setPhoneNumber(myclient.getPhoneNumber());
            client.setName(myclient.getName());
            Client save = clientRepository.save(client);
            return new Apiresponse("Found and updated",true,save);
        }
        return new Apiresponse("Client with such id not found",false);
    }
    public Apiresponse deleteClient(Integer id){
        if (clientRepository.existsById(id)) {
            clientRepository.deleteById(id);
            return new Apiresponse("Found and deleted",true);
        }
        return new Apiresponse("Client with this id does not exist",false);
    }
    public Apiresponse getOne(Integer id){
        if (clientRepository.existsById(id)) {
            Client client = clientRepository.findById(id).get();
            return new Apiresponse("Client",true,client);
        }
        return new Apiresponse("Client with this is not found",false);
    }
}
