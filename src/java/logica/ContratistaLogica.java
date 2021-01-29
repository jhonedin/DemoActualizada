/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.io.File;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import jxl.Sheet;
import jxl.Workbook;
import modelo.Contratista;
import persistencia.ContratistaFacadeLocal;

/**
 *
 * @author Jhon
 */
@Stateless
public class ContratistaLogica implements ContratistaLogicaLocal {

    @Override
    public void eliminarContratista(Contratista c) throws Exception {
        if(c == null){
            throw new Exception("Datos de contratista vacios");
            
        }
        if(c.getCodigocontratista() == 0){
            throw new Exception("El codigo es obligatorio");
        }
        // validar que el contratista no exista
        Contratista objContratista = contratistaDAO.findxNit(c.getNitcontratista());
        if(objContratista == null){
            throw new Exception("El contratista NO existe");
        }
        if(objContratista.getContratosList().size()>0
                || objContratista.getIngresoList().size()>0){
            throw new Exception("El contratista tiene contratos o ingresos asociados");
        }
        contratistaDAO.remove(c);
    }

    @EJB 
    private ContratistaFacadeLocal contratistaDAO;
    
    @Override
    public void registarContratista(Contratista contratista) throws Exception {
        if(contratista == null){
            throw new Exception("Datos de contratista vacios");
            
        }
        if(contratista.getNitcontratista() == 0){
            throw new Exception("El nit es obligatorio");
        }
        if(contratista.getNombrecontratista().equals("")){
            throw new Exception("El nombre es obligatorio");
        }
        
        Contratista objContratista = contratistaDAO.findxNit(contratista.getNitcontratista());
        if(objContratista != null){
            throw new Exception("El contratista ya existe");
        }
        contratistaDAO.create(contratista);
    
    }
    
    @Override
    public void modificarContratista(Contratista contratista) throws Exception{
        if(contratista == null){
            throw new Exception("Datos de contratista vacios");
            
        }
        if(contratista.getNitcontratista() == 0){
            throw new Exception("El nit es obligatorio");
        }
        if(contratista.getNombrecontratista().equals("")){
            throw new Exception("El nombre es obligatorio");
        }

        // verificar que el contratista exista 
        Contratista objContratista = contratistaDAO.find(contratista.getCodigocontratista());
        if(objContratista == null){
            throw new Exception("El contratista NO existe");
        }
        
        Contratista objModificar = contratistaDAO.findxNit(contratista.getNitcontratista());
        if(contratista.getNitcontratista() != objContratista.getNitcontratista()){
            if(objModificar != null){
                throw new Exception("El nit modificado ya existe");
            }
        }
        contratistaDAO.edit(contratista);
    }

    @Override
    public List<Contratista> consultarContratistas() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return contratistaDAO.findAll();
    }

    @Override
    public Contratista consultarxCodigo(Integer codigo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public String importarDatosContratista(String archivo) throws Exception {
        Workbook libro = Workbook.getWorkbook(new File(archivo));
        Sheet hoja = libro.getSheet(0);
        int filas = hoja.getRows();
        int contratistasN = 0, contratistasE=0;
        for(int i=4; i < filas;i++){
            Contratista nuevoContratista = new Contratista();
            nuevoContratista.setNitcontratista(
                Long.parseLong(hoja.getCell(0,i).getContents()));
            nuevoContratista.setNombrecontratista(
                hoja.getCell(1,i).getContents());
            Contratista objC = contratistaDAO.findxNit(nuevoContratista.getNitcontratista());
            if(objC==null){
                contratistaDAO.create(nuevoContratista);
                contratistasN++;
            }
            else{
                contratistasE++;
            }
        }
        return "Importanción correcta! contratistas creados "+contratistasN+"y Contratistas existentes "+contratistasE;
    }

    @Override
    public Contratista consultarxNit(Long nit) throws Exception {
        return contratistaDAO.findxNit(nit);
    }
}
