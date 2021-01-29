/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import logica.ContratistaLogicaLocal;
import modelo.Contratista;
import modelo.Contratos;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Jhon
 */
@Named(value = "contratistaVista")
@RequestScoped // tipo de vista cuando cree la clase
public class ContratistaVista {
    @EJB 
    private ContratistaLogicaLocal contratistaLogica;
    
    private List<Contratista> listaContratista;
    private List<Contratista> listaContratistasFiltered;
    // campos para registro 
    private InputText txtNitContratista;
    private InputText txtNombreContratista;
    private SelectOneMenu cmbEstadoContratista;
    private CommandButton btnRegistrar;
    private CommandButton btnModificar;
    private CommandButton btnEliminar;
    private CommandButton btnLimpiar;
    
    private Contratista selectedContratista;

    public Contratista getSelectedContratista() {
        return selectedContratista;
    }

    public void setSelectedContratista(Contratista selectedContratista) {
        this.selectedContratista = selectedContratista;
    }
    
    public void seleccionarContratista(SelectEvent event){
        selectedContratista = (Contratista) event.getObject();
        txtNitContratista.setValue(selectedContratista.getNitcontratista());
        txtNombreContratista.setValue(selectedContratista.getNombrecontratista());
        cmbEstadoContratista.setValue(selectedContratista.getEstadocontratista());
        btnModificar.setDisabled(false);
        btnEliminar.setDisabled(false);
        btnRegistrar.setDisabled(true);
        
    }
    
    public void subirArchivo(FileUploadEvent event) {
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String rutaDestino = (String) servletContext.getRealPath("/excel"); // Sustituye "/" por el directorio ej: "/upload"

        System.out.println("Ruta Server: " + rutaDestino);
        try {
            copyFile(rutaDestino, event.getFile().getFileName(), event.getFile().getInputstream());
            String resultado = contratistaLogica.importarDatosContratista(rutaDestino + "\\" + event.getFile().getFileName());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Ok: ", resultado));
           
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error",
                            ex.getMessage()));
        }
    }
    
    public void copyFile(String rutaDestino, String fileName, InputStream in) {
        try {
            OutputStream out = new FileOutputStream(new File(rutaDestino + "\\" + fileName));
            System.out.println("Ruta Archivo: " + rutaDestino + "\\" +fileName);
            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = in.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            in.close();
            out.flush();
            out.close();
            //System.out.println("New file created!");
        } catch (IOException e) {
            //System.out.println(e.getMessage());
        }
    }
    

    public InputText getTxtNitContratista() {
        return txtNitContratista;
    }

    public void setTxtNitContratista(InputText txtNitContratista) {
        this.txtNitContratista = txtNitContratista;
    }

    public InputText getTxtNombreContratista() {
        return txtNombreContratista;
    }

    public void setTxtNombreContratista(InputText txtNombreContratista) {
        this.txtNombreContratista = txtNombreContratista;
    }

    public SelectOneMenu getCmbEstadoContratista() {
        return cmbEstadoContratista;
    }

    public void setCmbEstadoContratista(SelectOneMenu cmbEstadoContratista) {
        this.cmbEstadoContratista = cmbEstadoContratista;
    }

    public CommandButton getBtnRegistrar() {
        return btnRegistrar;
    }

    public void setBtnRegistrar(CommandButton btnRegistrar) {
        this.btnRegistrar = btnRegistrar;
    }

    public CommandButton getBtnModificar() {
        return btnModificar;
    }

    public void setBtnModificar(CommandButton btnModificar) {
        this.btnModificar = btnModificar;
    }

    public CommandButton getBtnEliminar() {
        return btnEliminar;
    }

    public void setBtnEliminar(CommandButton btnEliminar) {
        this.btnEliminar = btnEliminar;
    }

    public CommandButton getBtnLimpiar() {
        return btnLimpiar;
    }

    public void setBtnLimpiar(CommandButton btnLimpiar) {
        this.btnLimpiar = btnLimpiar;
    }

    public void registrarContratista(){
        try {
            Contratista nuevoContratista = new Contratista();
            nuevoContratista.setNitcontratista(
                    Long.parseLong(txtNitContratista.getValue().toString()));
            nuevoContratista.setNombrecontratista(
                    txtNombreContratista.getValue().toString());
            nuevoContratista.setEstadocontratista(
                    cmbEstadoContratista.getValue().toString());
            contratistaLogica.registarContratista(nuevoContratista);
            limpiar();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,"Mensaje",
                            "Contratista registrado correctamente"));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error",
                            ex.getMessage()));
            //Logger.getLogger(contratistaVista.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
    
    public void limpiar(){
        txtNitContratista.setValue("");
        txtNitContratista.setValue("");
        txtNombreContratista.setValue("");
        cmbEstadoContratista.setValue("ACTIVO");
        btnModificar.setDisabled(true);
        btnEliminar.setDisabled(true);
        btnRegistrar.setDisabled(false);
    }
    
    public void modificarContratista() throws Exception{
        Contratista modContratista = selectedContratista;
        modContratista.setNitcontratista(
                Long.parseLong(txtNitContratista.getValue().toString()));
        modContratista.setNombrecontratista(
                txtNombreContratista.getValue().toString());
        modContratista.setEstadocontratista(
                cmbEstadoContratista.getValue().toString());
        contratistaLogica.modificarContratista(modContratista);
        limpiar();        
    }
    
    public void eliminarContratista(){
        
        try {
            Contratista objEliminar= selectedContratista;
            contratistaLogica.eliminarContratista(objEliminar);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,"Mensaje",
                            "Contratista eliminado correctamente"));
            limpiar();
        } catch (Exception ex) {
             FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error",
                            ex.getMessage()));
        }
    }
 
    
    /**
     * Creates a new instance of contratistaVista
     */
    public ContratistaVista() {
    }

    public List<Contratista> getListaContratista() {
        listaContratista = contratistaLogica.consultarContratistas();
        return listaContratista;
    }

    public void setListaContratista(List<Contratista> listaContratista) {
        this.listaContratista = listaContratista;
    }

    public ContratistaLogicaLocal getContratistaLogica() {
        return contratistaLogica;
    }

    public void setContratistaLogica(ContratistaLogicaLocal contratistaLogica) {
        this.contratistaLogica = contratistaLogica;
    }

    public List<Contratista> getListaContratistasFiltered() {
        return listaContratistasFiltered;
    }

    public void setListaContratistasFiltered(List<Contratista> listaContratistasFiltered) {
        this.listaContratistasFiltered = listaContratistasFiltered;
    }
    
 public void generarReporte() {
        try {
            Connection conn = null;
            Context initContext = new InitialContext();
            DataSource ds = (DataSource) initContext.lookup("java:app/jdbc/ingreso");
            conn = ds.getConnection();
            File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("admin/reportes/reporteContratistas.jasper"));
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), null, conn);
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            response.addHeader("Content-disposition", "attachment; filename=reporteContratistas.pdf");
            ServletOutputStream stream = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
            FacesContext.getCurrentInstance().responseComplete();
        } catch (NamingException ex) {
            Logger.getLogger(ContratistaVista.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ContratistaVista.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JRException ex) {
            Logger.getLogger(ContratistaVista.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ContratistaVista.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 
    public void generarReporteParametros() {
        try {
            Connection conn = null;
            Context initContext = new InitialContext();
            DataSource ds = (DataSource) initContext.lookup("java:app/jdbc/ingreso");
            conn = ds.getConnection();
            
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("nit", selectedContratista.getNitcontratista());
            parametros.put("nombre", selectedContratista.getNombrecontratista());
            parametros.put("estado", selectedContratista.getEstadocontratista());
            File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("admin/reportes/reporteIngresosContratista.jasper"));
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, conn);
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            response.addHeader("Content-disposition", "attachment; filename=reporteContratista" + selectedContratista.getNitcontratista() + ".pdf");
            ServletOutputStream stream = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
            FacesContext.getCurrentInstance().responseComplete();
        } catch (NamingException ex) {
            Logger.getLogger(ContratistaVista.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ContratistaVista.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JRException ex) {
            Logger.getLogger(ContratistaVista.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ContratistaVista.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

        public void generarReporteListas() {
        try {
            Contratista objC = contratistaLogica.consultarxNit(selectedContratista.getNitcontratista());
            List<Contratos> listaContratista = objC.getContratosList();
            
           
               Map<String, Object> parametros = new HashMap<>();
                parametros.put("nit", objC.getNitcontratista());
                parametros.put("nombre", objC.getNombrecontratista());
                parametros.put("estado", objC.getNombrecontratista());
                File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("admin/reportes/reporteContratosContratista.jasper"));
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, new JRBeanCollectionDataSource(listaContratista));
                HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
                response.addHeader("Content-disposition", "attachment; filename=reporteContratosContratrista"+objC.getNitcontratista()+".pdf");
                ServletOutputStream stream = response.getOutputStream();
                JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
                FacesContext.getCurrentInstance().responseComplete();
        } catch (NamingException ex) {
            Logger.getLogger(ContratistaVista.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ContratistaVista.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JRException ex) {
            Logger.getLogger(ContratistaVista.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ContratistaVista.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ContratistaVista.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  
    
}
