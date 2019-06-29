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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import logica.ContratistaLogicaLocal;
import modelo.Contratista;
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
    
    
    
}
