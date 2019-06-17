/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import modelo.Empleado;

/**
 *
 * @author Jhon
 */
@Stateless
public class EmpleadoFacade extends AbstractFacade<Empleado> implements EmpleadoFacadeLocal {

    @PersistenceContext(unitName = "DemoActualizadaPU")
    private EntityManager em;

    /**
     *
     * @param cedula
     * @return
     */
    @Override
    public Empleado findxCedula(long cedula){
        try{
        String consulta = "select c from Empleado c where c.cedulaempleado = "+cedula;
        Query query = em.createQuery(consulta);
        return (Empleado) query.getSingleResult();
        } catch(NoResultException nre){
            return null;
        }
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EmpleadoFacade() {
        super(Empleado.class);
    }
    
}
