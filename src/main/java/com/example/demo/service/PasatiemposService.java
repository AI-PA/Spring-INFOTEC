package com.example.demo.service;

import com.example.demo.entity.Pasatiempos;
import com.example.demo.repository.PasatiemposRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PasatiemposService {
    @Autowired
    PasatiemposRepository pasatiemposRepository;
    //CRUD
    public Pasatiempos create(Pasatiempos pasatiempos){
        return pasatiemposRepository.save(pasatiempos);
    }
    public List<Pasatiempos> getAllPasatiempos(){
        return pasatiemposRepository.findAll();
    }
    public void delete(Pasatiempos pasatiempos){
        pasatiemposRepository.delete(pasatiempos);
    }
    public Pasatiempos findById(Integer id){
        return pasatiemposRepository.findById(id).get();
    }
}
