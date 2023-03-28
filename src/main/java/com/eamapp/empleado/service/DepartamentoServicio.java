package com.eamapp.empleado.service;

import java.util.List;

import com.eamapp.empleado.entity.Departamento;


public interface DepartamentoServicio  {
    
    public List<Departamento> listarTodosLosDepartamentos();

    public Departamento guardarDepartamento(Departamento departamento);

    public Departamento obtenerDepartamentoPorCodigo(Long codigo);

    public Departamento actualizarDepartamento(Departamento departamento);

    public void eliminarDepartamento(Long id);

}
