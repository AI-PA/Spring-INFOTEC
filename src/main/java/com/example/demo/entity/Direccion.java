package com.example.demo.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="direccion",schema ="public")
public class Direccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "calle")
    private String calle;
    @Column(name = "numero_interior")
    private String numero_interior;
    @Column(name = "numero_exterior")
    private String numero_exterior;
    @Column(name = "codigo_postal")
    private Integer codigo_postal;
    @Column(name = "colonia")
    private String colonia;
    @Column(name = "ciudad")
    private Integer ciudad;
    @Column(name = "estado")
    private Integer estado;

    @OneToMany(mappedBy = "direccion",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Persona> personas;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumero_interior() {
        return numero_interior;
    }

    public void setNumero_interior(String numero_interior) {
        this.numero_interior = numero_interior;
    }

    public String getNumero_exterior() {
        return numero_exterior;
    }

    public void setNumero_exterior(String numero_exterior) {
        this.numero_exterior = numero_exterior;
    }

    public Integer getCodigo_postal() {
        return codigo_postal;
    }

    public void setCodigo_postal(Integer codigo_postal) {
        this.codigo_postal = codigo_postal;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public Integer getCiudad() {
        return ciudad;
    }

    public void setCiudad(Integer ciudad) {
        this.ciudad = ciudad;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }
}
