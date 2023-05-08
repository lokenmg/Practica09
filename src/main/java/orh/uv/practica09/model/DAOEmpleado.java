/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package orh.uv.practica09.model;

import org.springframework.data.jpa.repository.JpaRepository;


/**
 *
 * @author loken
 */
public interface DAOEmpleado extends JpaRepository<Empleado, Long>{
    
}
