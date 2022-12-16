package com.example.demo.service;
import com.example.demo.entity.Persona;
import com.example.demo.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonaService {
    @Autowired
    private PersonaRepository personaRepository;
    public PersonaService(PersonaRepository personaRepository){
        this.personaRepository = personaRepository;
    }
    //CRUD
    public Persona create(Persona persona){
        return personaRepository.save(persona);
    }
    public List<Persona> getAllPersonas(){
        return personaRepository.findAll();
    }
    public void delete(Persona persona){
         personaRepository.delete(persona);
    }
    public Persona findById(Integer id){
        return personaRepository.findById(id).get();
    }


}
