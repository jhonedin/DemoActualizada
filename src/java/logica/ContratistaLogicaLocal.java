/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.List;
import javax.ejb.Local;
import modelo.Contratista;

/**
 *
 * @author Jhon
 */
@Local
public interface ContratistaLogicaLocal {
    public void registarContratista(Contratista contratista) // estas son interfaces
        throws Exception;
    public void modificarContratista(Contratista contratista)
        throws Exception;
    public List<Contratista> consultarContratistas();
    public Contratista consultarxCodigo(Integer codigo)
        throws Exception;
    public Contratista consultarxNit(Long nit) throws Exception;
    public void eliminarContratista(Contratista c) throws Exception;
    public String importarDatosContratista(String archivo) throws Exception;

}