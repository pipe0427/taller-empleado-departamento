package com.eamapp.empleado.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.eamapp.empleado.entity.Departamento;
import com.eamapp.empleado.entity.Empleado;
import com.eamapp.empleado.service.DepartamentoServicio;
import com.eamapp.empleado.service.EmpleadoServicio;

@Controller
public class EmpleadoController {
    @Autowired
    private EmpleadoServicio servicio;

    @Autowired
    private DepartamentoServicio servicioDepartamento;

    @GetMapping({ "/empleados", "/" })
    public String listarEmpleados(Model modelo) {
        modelo.addAttribute("empleados", servicio.listarTodosLosEmpleados());
        return "empleados"; // nos retorna al archivo empleados
    }
    
    
    @GetMapping("/empleados/nuevo")
    public String mostrarFormularioDeRegistrtarEstudiante(Model modelo) {
        Empleado empleado = new Empleado();
        modelo.addAttribute("empleado", empleado);
        modelo.addAttribute("departamentos", servicioDepartamento.listarTodosLosDepartamentos());
        return "crear_empleado";
    }

    @PostMapping("/empleados")
    public String guardarEmpleado(@ModelAttribute("empleado") Empleado empleado) {
        servicio.guardarEmpleado(empleado);
        return "redirect:/empleados";
    }

    @GetMapping("/empleados/editar/{id}")
    public String mostrarFormularioDeEditar(@PathVariable Long id, Model modelo,@ModelAttribute("departamentos") Departamento departamento) {
        modelo.addAttribute("empleado", servicio.obtenerEmpleadoPorId(id));
        modelo.addAttribute("departamentos", servicioDepartamento.listarTodosLosDepartamentos());
        return "editar_empleado";
    }

    @PostMapping("/empleados/{id}")
    public String actualizarEstudiante(@PathVariable Long id, @ModelAttribute("empleado") Empleado empleado,Model modelo) {
        Empleado empleadoExistente = servicio.obtenerEmpleadoPorId(id);
        empleadoExistente.setNif(empleado.getNif());
        empleadoExistente.setNombre(empleado.getNombre());
        empleadoExistente.setApellido1(empleado.getApellido1());
        empleadoExistente.setApellido2(empleado.getApellido2());
        empleadoExistente.setCodigo_departamento(empleado.getCodigo_departamento());

        servicio.actualizarEmpleado(empleadoExistente);
        return "redirect:/empleados";
    }

    @GetMapping("/empleados/{id}")
    public String eliminarEmpleado(@PathVariable Long id) {
        servicio.eliminarEmpleado(id);
        return "redirect:/empleados";
    }
}
