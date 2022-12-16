package com.example.demo.controller;

import com.example.demo.entity.Persona;
import com.example.demo.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/persona/")
public class PersonaController {
    @Autowired
    public PersonaService personaService;

    @PostMapping
    private ResponseEntity<Persona> guardar (@RequestBody Persona persona){
        Persona temporal = personaService.create(persona);
        try {
            return ResponseEntity.created(new URI("/api/persona"+temporal.getId())).body(temporal);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @GetMapping
    private ResponseEntity<List<Persona>> listarTodasLasPersonas (){
        return ResponseEntity.ok(personaService.getAllPersonas());
    }
    @DeleteMapping
    private ResponseEntity<Void> eliminarPersona (@RequestBody Persona persona) {
        personaService.delete(persona);
        return ResponseEntity.ok().build();
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    private ResponseEntity<Persona> listarPersonasByID (@PathVariable("id") Integer id){
        return ResponseEntity.ok(personaService.findById(id));
    }
}
