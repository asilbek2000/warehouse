package uz.pdp.appproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appproject.entity.Measurment;
import uz.pdp.appproject.repository.MeasurmentRepository;
import uz.pdp.appproject.response.Apiresponse;

import java.util.List;

@Service
public class MeasurmentService {
    @Autowired
    MeasurmentRepository measurmentRepository;
    public Apiresponse getAll(){
        List<Measurment> all = measurmentRepository.findAll();
        return new Apiresponse("List of measurments",true,all);
    }
    public Apiresponse addMeasurment(Measurment mymeasurment){
        if (measurmentRepository.existsByName(mymeasurment.getName())) {
            return new Apiresponse("Measurment with this name already exist",false);
        }
        Measurment measurment=new Measurment();
        measurment.setName(mymeasurment.getName());
        measurment.setActive(mymeasurment.getActive());
        Measurment save = measurmentRepository.save(measurment);
        return new Apiresponse("Added successfully",true,save);
    }
    public Apiresponse editMeasurment(Integer id,Measurment mymeasurment){
        if (measurmentRepository.existsById(id)) {
            Measurment measurment = measurmentRepository.findById(id).get();
            measurment.setActive(mymeasurment.getActive());
            measurment.setName(mymeasurment.getName());
            Measurment save = measurmentRepository.save(measurment);
            return new Apiresponse("Found and updated",true,save);
        }
        return new Apiresponse("Measurment with this id not found",false);
    }
    public Apiresponse deleteMeasurment(Integer id){
        if (measurmentRepository.existsById(id)) {
            measurmentRepository.deleteById(id);
            return new Apiresponse("Found and deleted",true);
        }
        return new Apiresponse("Measurment with this id not found",false);
    }
    public Apiresponse getOne(Integer id){
        if (measurmentRepository.existsById(id)) {
            Measurment measurment = measurmentRepository.findById(id).get();
            return new Apiresponse("Measurmenrt",true,measurment);
        }
        return new Apiresponse("Measurment with this id not found",false);
    }
}
