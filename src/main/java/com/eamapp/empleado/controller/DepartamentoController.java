package com.eamapp.empleado.controller;  

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.eamapp.empleado.service.DepartamentoServicio;
import com.eamapp.empleado.entity.Departamento;



@Controller
public class DepartamentoController {

    @Autowired
    private DepartamentoServicio servicio;

    @GetMapping({ "/departamentos"})
    public String listarDepartamentos(Model modelo) {
        modelo.addAttribute("departamentos", servicio.listarTodosLosDepartamentos());
        return "departamentos"; // nos retorna el html que tengamos
    }
    
    @GetMapping("/departamentos/nuevo")
    public String mostrarFormularioDeRegistrtarDepartamento(Model modelo) {
        Departamento departamento = new Departamento();
        modelo.addAttribute("departamento", departamento);
        return "crear_departamento";
    }

    @PostMapping("/departamentos")
    public String guardarDepartamento(@ModelAttribute("departamento") Departamento departamento) {
        servicio.guardarDepartamento(departamento);
        return "redirect:/departamentos";
    }

    @GetMapping("/departamentos/editar/{codigo}")
    public String mostrarFormularioDeEditar(@PathVariable Long codigo, Model modelo) {
        modelo.addAttribute("departamento", servicio.obtenerDepartamentoPorCodigo(codigo));
        return "editar_departamento";
    }

    @PostMapping("/departamentos/{codigo}")
    public String actualizarDepartamento(@PathVariable Long codigo, @ModelAttribute("departamento") Departamento departamento,
                                       Model modelo) {
        Departamento departamentoExistente = servicio.obtenerDepartamentoPorCodigo(codigo);
        departamentoExistente.setCodigo(codigo);
    
        departamentoExistente.setNombre(departamento.getNombre());

        departamentoExistente.setGastos(departamento.getGastos());

        departamentoExistente.setPresupuesto(departamento.getPresupuesto());
 

        servicio.actualizarDepartamento(departamentoExistente);
        return "redirect:/departamentos";
    }

    @GetMapping("/departamentos/{codigo}")
    public String eliminarDepartamento(@PathVariable Long codigo) {
        servicio.eliminarDepartamento(codigo);
        return "redirect:/departamentos";
    }
    
}
