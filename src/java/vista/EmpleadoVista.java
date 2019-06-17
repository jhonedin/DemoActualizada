/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import logica.EmpleadoLogicaLocal;
import modelo.Empleado;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Jhon
 */
@Named(value = "empleadoVista")
@RequestScoped
public class EmpleadoVista {

    @EJB 
    private EmpleadoLogicaLocal empleadoLogica; 
    private List<Empleado> listaEmpleado;
    private List<Empleado> listaEmpleadoFiltered;
    private Empleado selectedEmpleado;
    // campos para registro 
    private InputText txtCedulaEmpleado;
    private InputText txtNombreEmpleado;
    private InputText txtApellidoEmpleado;
    private InputText txtTelefonoEmpleado;
    private SelectOneMenu cmbEstadoEmpleado;
    private CommandButton btnRegistrar;
    private CommandButton btnModificar;
    private CommandButton btnEliminar;
    private CommandButton btnLimpiar;
    
    public void registrarEmpleado(){
        try {
            Empleado nuevoEmpleado = new Empleado();
            nuevoEmpleado.setCedulaempleado(Long.parseLong(txtCedulaEmpleado.getValue().toString()));
            nuevoEmpleado.setNombreempleado(txtNombreEmpleado.getValue().toString());
            nuevoEmpleado.setApellidoempleado(txtApellidoEmpleado.getValue().toString());
            nuevoEmpleado.setTelefonoempleado(txtTelefonoEmpleado.getValue().toString());
            nuevoEmpleado.setEstadoempleado(cmbEstadoEmpleado.getValue().toString());
            empleadoLogica.registarEmpleado(nuevoEmpleado);
            limpiar();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,"Mensaje",
                            "Empleado registrado correctamente"));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error",
                            ex.getMessage()));         
        }    
    }
    
    public void modificarEmpleado() throws Exception{
        Empleado modEmpleado = selectedEmpleado;
        modEmpleado.setCedulaempleado(Long.parseLong(txtCedulaEmpleado.getValue().toString()));
        modEmpleado.setNombreempleado(txtNombreEmpleado.getValue().toString());
        modEmpleado.setApellidoempleado(txtApellidoEmpleado.getValue().toString());
        modEmpleado.setTelefonoempleado(txtTelefonoEmpleado.getValue().toString());
        modEmpleado.setEstadoempleado(cmbEstadoEmpleado.getValue().toString());
        empleadoLogica.modificarEmpleado(modEmpleado);
        limpiar();        
    }
        
    public void eliminarEmpleado(){  
        try {
            Empleado objEmpleado= selectedEmpleado;
            empleadoLogica.eliminarEmpleado(objEmpleado);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,"Mensaje",
                            "Empleado eliminado correctamente"));
            limpiar();
        } catch (Exception ex) {
             FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error",
                            ex.getMessage()));
        }
    }
    
    public void limpiar(){
        txtCedulaEmpleado.setValue("");
        txtNombreEmpleado.setValue("");
        txtApellidoEmpleado.setValue("");
        txtTelefonoEmpleado.setValue("");
        cmbEstadoEmpleado.setValue("ACTIVO");
    }


    public EmpleadoLogicaLocal getEmpleadoLogica() {
        return empleadoLogica;
    }

    public void setEmpleadoLogica(EmpleadoLogicaLocal empleadoLogica) {
        this.empleadoLogica = empleadoLogica;
    }

    public List<Empleado> getListaEmpleado() {
        listaEmpleado = empleadoLogica.consultarEmpleados();
        return listaEmpleado;
    }

    public void setListaEmpleado(List<Empleado> listaEmpleado) {
        this.listaEmpleado = listaEmpleado;
    }

    public List<Empleado> getListaEmpleadoFiltered() {
        return listaEmpleadoFiltered;
    }

    public void setListaEmpleadoFiltered(List<Empleado> listaEmpleadoFiltered) {
        this.listaEmpleadoFiltered = listaEmpleadoFiltered;
    }

    public Empleado getSelectedEmpleado() {
        return selectedEmpleado;
    }

    public void setSelectedEmpleado(Empleado selectedEmpleado) {
        this.selectedEmpleado = selectedEmpleado;
    }
    
    public void seleccionarEmpleado(SelectEvent event){
        //selectedEmpleado = new Empleado();
        selectedEmpleado = (Empleado) event.getObject();
        txtCedulaEmpleado.setValue(selectedEmpleado.getCedulaempleado());
        txtNombreEmpleado.setValue(selectedEmpleado.getNombreempleado());
        txtApellidoEmpleado.setValue(selectedEmpleado.getApellidoempleado());
        txtTelefonoEmpleado.setValue(selectedEmpleado.getTelefonoempleado());
        cmbEstadoEmpleado.setValue(selectedEmpleado.getEstadoempleado());
        
    }

    public InputText getTxtCedulaEmpleado() {
        return txtCedulaEmpleado;
    }

    public void setTxtCedulaEmpleado(InputText txtCedulaEmpleado) {
        this.txtCedulaEmpleado = txtCedulaEmpleado;
    }

    public InputText getTxtNombreEmpleado() {
        return txtNombreEmpleado;
    }

    public void setTxtNombreEmpleado(InputText txtNombreEmpleado) {
        this.txtNombreEmpleado = txtNombreEmpleado;
    }

    public InputText getTxtApellidoEmpleado() {
        return txtApellidoEmpleado;
    }

    public void setTxtApellidoEmpleado(InputText txtApellidoEmpleado) {
        this.txtApellidoEmpleado = txtApellidoEmpleado;
    }

    public InputText getTxtTelefonoEmpleado() {
        return txtTelefonoEmpleado;
    }

    public void setTxtTelefonoEmpleado(InputText txtTelefonoEmpleado) {
        this.txtTelefonoEmpleado = txtTelefonoEmpleado;
    }

    public SelectOneMenu getCmbEstadoEmpleado() {
        return cmbEstadoEmpleado;
    }

    public void setCmbEstadoEmpleado(SelectOneMenu cmbEstadoEmpleado) {
        this.cmbEstadoEmpleado = cmbEstadoEmpleado;
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
    
    
   
    /**
     * Creates a new instance of EmpleadoVista
     */
    public EmpleadoVista() {
    }
    
    
}
