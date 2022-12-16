package com.example.demo.controller;

import com.example.demo.entity.Direccion;
import com.example.demo.service.DireccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/direccion/")
public class DireccionController {
    @Autowired
    public DireccionService direccionService;
    @PostMapping
    private ResponseEntity<Direccion> guardar (@RequestBody Direccion direccion){
        Direccion temporal = direccionService.create(direccion);
        try {
            return ResponseEntity.created(new URI("/api/direccion"+temporal.getId())).body(temporal);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @GetMapping
    private ResponseEntity<List<Direccion>> listarTodasLasDirecciones (){
        return ResponseEntity.ok(direccionService.getAllDirecciones());
    }
    @DeleteMapping
    private ResponseEntity<Void> eliminarDireccion (@RequestBody Direccion direccion) {
        direccionService.delete(direccion);
        return ResponseEntity.ok().build();
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    private ResponseEntity<Direccion> listarDireccionesByID (@PathVariable("id") Integer id){
        return ResponseEntity.ok(direccionService.findById(id));
    }
}
