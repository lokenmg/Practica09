 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package orh.uv.practica09.controllers;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import orh.uv.practica09.model.DAOEmpleado;
import orh.uv.practica09.model.Empleado;
import orh.uv.practica09.model.EmpleadoDTO;

/**
 *
 * @author loken
 */
@RestController
public class EmpleadoController {
     @Autowired
    DAOEmpleado empleadoRepository;

    @GetMapping("/empleado")
    public List<Empleado> lsEmpleados() {
        return empleadoRepository.findAll();
    }
    

    @GetMapping("/empleado/{id}")
    public ResponseEntity<Empleado> byIdEmpleados(@PathVariable Long id) {
        Optional<Empleado> opt = empleadoRepository.findById(id);

        if (opt.isPresent()) {
            return new ResponseEntity<>(opt.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/empleado")
    public ResponseEntity<Empleado> addEmpleado(@RequestBody EmpleadoDTO empleadoDTO) {
        Empleado empleado = new Empleado(empleadoDTO);
        try {
            Empleado eMpleado = empleadoRepository.save(empleado);
            return new ResponseEntity<>(eMpleado, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/empleado/{id}")
    public ResponseEntity<HttpStatus> deleteVisit(@PathVariable Long id) {
        try {
            empleadoRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/empleado/{id}")
    public ResponseEntity<Empleado> updateEmpleado(@PathVariable("id") long id, @RequestBody EmpleadoDTO empleadoDTO) {
        Empleado empleado = new Empleado(empleadoDTO);  
        Optional<Empleado> empleadoData = empleadoRepository.findById(id);

        if (empleadoData.isPresent()) {
            return new ResponseEntity<>(empleadoRepository.saveAndFlush(empleado), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
