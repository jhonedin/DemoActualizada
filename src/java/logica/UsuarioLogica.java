/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import modelo.Usuario;
import org.apache.commons.codec.digest.DigestUtils;
import persistencia.UsuarioFacadeLocal;

/**
 *
 * @author Jhon
 */
@Stateless
public class UsuarioLogica implements UsuarioLogicaLocal {

    @EJB
    private UsuarioFacadeLocal usuarioDAO;
    
    @Override
    public Usuario ingresar(Usuario usuario) throws Exception {
        if(usuario == null){
            throw new Exception("Usuario sin datos");
        }
        if(usuario.getNombreusuario().equals("")){
            throw new Exception("Nombre Obligatorio");
        }
        if(usuario.getClaveusuario().equals("")){
            throw new Exception("Clave obligatoria");
        }
        
        Usuario objUsuario = usuarioDAO.findxNombre(usuario.getNombreusuario());
        if(objUsuario == null){
            throw new Exception("Usuario incorrecto");
        }
        //validar clave
        String encriptada = encriptarContraseña(usuario.getClaveusuario());
        if(!objUsuario.getClaveusuario().equals(encriptada)){
            throw new Exception("Clave incorrecta");
        }
        return objUsuario;
        
    }
    
    
    
    public String encriptarContraseña(String password) {
        String encriptMD5 = DigestUtils.md5Hex(password);
        System.out.println("md5:" + encriptMD5);
        
        return encriptMD5;
    }
    
    
}
