/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.List;
import javax.ejb.Local;
import modelo.Empleado;

/**
 *
 * @author Jhon
 */
@Local
public interface EmpleadoLogicaLocal {
    public void registarEmpleado(Empleado e) throws Exception;
    public void modificarEmpleado(Empleado e) throws Exception;
    public List<Empleado> consultarEmpleados();
    public Empleado consultarxCedula(Integer cedula) throws Exception;
    public void eliminarEmpleado(Empleado e) throws Exception;
}
