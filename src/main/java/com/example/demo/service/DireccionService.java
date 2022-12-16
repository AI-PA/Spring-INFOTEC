package com.example.demo.service;

import com.example.demo.entity.Direccion;
import com.example.demo.repository.DireccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DireccionService {
    @Autowired
    private DireccionRepository direccionRepository;
    //CRUD
    public Direccion create(Direccion direccion){
        return direccionRepository.save(direccion);
    }
    public List<Direccion> getAllDirecciones(){
        return direccionRepository.findAll();
    }
    public void delete(Direccion direccion){
        direccionRepository.delete(direccion);
    }
    public Direccion findById(Integer id){
        return direccionRepository.findById(id).get();
    }
}
