package com.example.demo.controller;

import com.example.demo.entity.Pasatiempos;
import com.example.demo.service.PasatiemposService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/pasatiempos/")
public class PasatiemposController {
    @Autowired
    public PasatiemposService pasatiemposService;
    @PostMapping
    private ResponseEntity<Pasatiempos> guardar (@RequestBody Pasatiempos pasatiempos){
        Pasatiempos temporal = pasatiemposService.create(pasatiempos);
        try {
            return ResponseEntity.created(new URI("/api/pasatiempos"+temporal.getId())).body(temporal);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @GetMapping
    private ResponseEntity<List<Pasatiempos>> listarTodasLosPasatiempos (){
        return ResponseEntity.ok(pasatiemposService.getAllPasatiempos());
    }
    @DeleteMapping
    private ResponseEntity<Void> eliminarPasatiempos (@RequestBody Pasatiempos pasatiempos) {
        pasatiemposService.delete(pasatiempos);
        return ResponseEntity.ok().build();
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    private ResponseEntity<Pasatiempos> listarPasatiemposByID (@PathVariable("id") Integer id){
        return ResponseEntity.ok(pasatiemposService.findById(id));
    }
}
