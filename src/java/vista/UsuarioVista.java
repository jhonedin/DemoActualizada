/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import logica.UsuarioLogicaLocal;
import modelo.Usuario;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.password.Password;

/**
 *
 * @author Jhon
 */
@Named(value = "usuarioVista")
@RequestScoped
public class UsuarioVista {
    @EJB 
    private UsuarioLogicaLocal usuarioLogica;
    
    private InputText txtUsuario;
    private Password txtPassword;
    private CommandButton btnIngresar;

    public UsuarioLogicaLocal getUsuarioLogica() {
        return usuarioLogica;
    }

    public void setUsuarioLogica(UsuarioLogicaLocal usuarioLogica) {
        this.usuarioLogica = usuarioLogica;
    }

    public InputText getTxtUsuario() {
        return txtUsuario;
    }

    public void setTxtUsuario(InputText txtUsuario) {
        this.txtUsuario = txtUsuario;
    }

    public Password getTxtPassword() {
        return txtPassword;
    }

    public void setTxtPassword(Password txtPassword) {
        this.txtPassword = txtPassword;
    }

    public CommandButton getBtnIngresar() {
        return btnIngresar;
    }

    public void setBtnIngresar(CommandButton btnIngresar) {
        this.btnIngresar = btnIngresar;
    }
    
    public void ingresar(){
        try {
            Usuario usuario = new Usuario();
            usuario.setNombreusuario(txtUsuario.getValue().toString());
            usuario.setClaveusuario(txtPassword.getValue().toString());
            Usuario logueado = usuarioLogica.ingresar(usuario);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", logueado);
            FacesContext.getCurrentInstance().getExternalContext().redirect("admin/gestionContratistas.xhtml");
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error",
                            ex.getMessage())); 
        }
    }
    
    public void cerrarSesion(){
        try {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("usuario");
            FacesContext.getCurrentInstance().getExternalContext().redirect("../index.xhtml");
        } catch (IOException ex) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error",
                            ex.getMessage())); 
        }
    }
    
    
    public UsuarioVista() {
        
    }
    
}
