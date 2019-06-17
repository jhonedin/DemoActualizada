/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.List;
import javax.ejb.Local;
import modelo.Ingreso;

/**
 *
 * @author Jhon
 */
@Local
public interface IngresoLogicaLocal {
    public void registrarIngreso(Ingreso ingreso) throws Exception;
    public void modificarIngreso(Ingreso ingreso) throws Exception;
    public List<Ingreso> consultarIngresos();
    public void consultarContratistaxNit() throws Exception;
    public void consultarEmpleadoxNit() throws Exception;
    public void eliminarContratista(Ingreso c) throws Exception;
}
