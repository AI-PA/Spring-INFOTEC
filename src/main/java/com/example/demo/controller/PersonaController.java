package com.example.demo.controller;

import com.example.demo.entity.Persona;
import com.example.demo.service.PersonaService;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.event.DocumentListener;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
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
    //Generar PDF
    @RequestMapping(value = "/generarPDF", method = RequestMethod.GET, produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> generarPDF() throws IOException{
        List<Persona> personas = personaService.getAllPersonas();
        ByteArrayInputStream bis = createPdf(personas);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=personas.pdf");
        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bis));
    }
    private ByteArrayInputStream createPdf(List<Persona> personas) throws IOException{
        Document document = new Document() {
            @Override
            public int getLength() {
                return 0;
            }

            @Override
            public void addDocumentListener(DocumentListener listener) {

            }

            @Override
            public void removeDocumentListener(DocumentListener listener) {

            }

            @Override
            public void addUndoableEditListener(UndoableEditListener listener) {

            }

            @Override
            public void removeUndoableEditListener(UndoableEditListener listener) {

            }

            @Override
            public Object getProperty(Object key) {
                return null;
            }

            @Override
            public void putProperty(Object key, Object value) {

            }

            @Override
            public void remove(int offs, int len) throws BadLocationException {

            }

            @Override
            public void insertString(int offset, String str, AttributeSet a) throws BadLocationException {

            }

            @Override
            public String getText(int offset, int length) throws BadLocationException {
                return null;
            }

            @Override
            public void getText(int offset, int length, Segment txt) throws BadLocationException {

            }

            @Override
            public Position getStartPosition() {
                return null;
            }

            @Override
            public Position getEndPosition() {
                return null;
            }

            @Override
            public Position createPosition(int offs) throws BadLocationException {
                return null;
            }

            @Override
            public Element[] getRootElements() {
                return new Element[0];
            }

            @Override
            public Element getDefaultRootElement() {
                return null;
            }

            @Override
            public void render(Runnable r) {

            }
        };
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter.getInstance((com.lowagie.text.Document) document, (OutputStream) out);
        document.open();

        for(Persona persona: personas ){
            document.add(new Paragraph(persona.get("id").toString()));
            document.add(new Paragraph(persona.get("nombre").toString()));
            document.add(new Paragraph(persona.get("apellidos").toString()));
            document.add(new Paragraph(persona.get("direccion_id").toString()));

        }
        document.close();
        return new ByteArrayInputStream(out.toByteArray());
    }
}
