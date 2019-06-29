/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import modelo.Empleado;
import persistencia.EmpleadoFacadeLocal;


/**
 *
 * @author Jhon
 */
@Stateless
public class EmpleadoLogica implements EmpleadoLogicaLocal {

    @EJB 
    private EmpleadoFacadeLocal empleadoDAO;
    
    @Override
    public void registarEmpleado(Empleado empleado) throws Exception {
        if(empleado == null){
            throw new Exception("Datos de contratista vacios");    
        }
        if(empleado.getCedulaempleado() == 0){
            throw new Exception("La cedula es obligatoria");
        }
        if(empleado.getNombreempleado().equals(" ")){
            throw new Exception("El nombre es obligatorio");
        }
        
        Empleado objEmpleado = empleadoDAO.findxCedula(empleado.getCedulaempleado());
        if(objEmpleado != null){
            throw new Exception("El empleado ya existe");
        }
        empleadoDAO.create(empleado);   
    }

    @Override
    public void modificarEmpleado(Empleado empleado) throws Exception {
        if(empleado == null){
            throw new Exception("Datos de empleado vacios");
            
        }
        if(empleado.getCedulaempleado() == 0){
            throw new Exception("La cedula es obligatoria");
        }
        if(empleado.getNombreempleado().equals("")){
            throw new Exception("El nombre es obligatorio");
        }

        // verificar que el empleado exista 
        Empleado objContratista = empleadoDAO.find(empleado.getCodigoempleado());
        if(objContratista == null){
            throw new Exception("El contratista NO existe");
        }
        
        Empleado objModificar = empleadoDAO.findxCedula(empleado.getCedulaempleado());
        if(empleado.getCedulaempleado() != objContratista.getCedulaempleado()){
            if(objModificar != null){
                throw new Exception("La cedula modificada ya existe");
            }
        }
        empleadoDAO.edit(empleado);
    }

    @Override
    public List<Empleado> consultarEmpleados() {
        return empleadoDAO.findAll();
    }

    @Override
    public Empleado consultarxCedula(Integer cedula) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminarEmpleado(Empleado e) throws Exception {
        if(e == null){
            throw new Exception("Datos de empleado vacios");
            
        }
        if(e.getCedulaempleado() == 0){
            throw new Exception("La cedula es obligatoria");
        }
        // validar que el contratista no exista
        Empleado objEmpleado = empleadoDAO.findxCedula(e.getCedulaempleado());
        if(objEmpleado == null){
            throw new Exception("El empleado NO existe");
        }
        empleadoDAO.remove(e);
    }


    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
