package uz.pdp.appproject.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appproject.entity.User;
import uz.pdp.appproject.entity.Warehouse;
import uz.pdp.appproject.payload.UserDto;
import uz.pdp.appproject.repository.UserRepository;
import uz.pdp.appproject.repository.WarehouseRepository;
import uz.pdp.appproject.response.Apiresponse;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService  {
    @Autowired
    UserRepository userRepository;
    @Autowired
    WarehouseRepository warehouseRepository;
    public Apiresponse getAll(){
        List<User> all = userRepository.findAll();
        return new Apiresponse("List of users",true,all);
    }
    public Apiresponse addUser(UserDto dto){
        if (userRepository.existsByPhoneNumber(dto.getPhoneNumber())) {
            return new Apiresponse("User with this phoneNumber already exist",false);
        }
        List<Warehouse> allById = warehouseRepository.findAllById(dto.getWarehousesId());
        Set<Warehouse> warehouseSet=new HashSet<>(allById);
        User user=new User();
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setCode(dto.getCode());
        user.setPassword(dto.getPassword());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setActive(dto.isActive());
        user.setWarehouses(warehouseSet);
        User save = userRepository.save(user);
        return new Apiresponse("Added successfully",true,save);

    }
    public Apiresponse editUser(Integer id,UserDto dto){
        if (userRepository.existsById(id)) {
            List<Warehouse> allById = warehouseRepository.findAllById(dto.getWarehousesId());
            Set<Warehouse>warehousesSet=new HashSet<>(allById);
            User user = userRepository.findById(id).get();
            user.setFirstName(dto.getFirstName());
            user.setLastName(dto.getLastName());
            user.setPhoneNumber(dto.getPhoneNumber());
            user.setPassword(dto.getPassword());
            user.setActive(dto.isActive());
            user.setCode(dto.getCode());
            user.setWarehouses(warehousesSet);
            User save = userRepository.save(user);
            return new Apiresponse("Found and updated",true,save);
        }
        return new Apiresponse("User with this id not found",false);

    }
    public Apiresponse deleteUser(Integer id){
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return new Apiresponse("Found and deleted",true);
        }
        return new Apiresponse("User with this id not found",false);
    }
    public Apiresponse getOne(Integer id){
        if (userRepository.existsById(id)) {
            User user = userRepository.findById(id).get();
            return new Apiresponse("User",true,user);
        }
        return new Apiresponse("User with this id not found",false);
    }
}
