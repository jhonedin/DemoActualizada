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
import modelo.Contratista;

/**
 *
 * @author Jhon
 */
@Stateless
public class ContratistaFacade extends AbstractFacade<Contratista> implements ContratistaFacadeLocal {

    @PersistenceContext(unitName = "DemoActualizadaPU")
    private EntityManager em;
    
   
    @Override
    public Contratista findxNit(long nit){
        try{
        String consulta = "select c from Contratista c where c.nitcontratista = "+nit;
        Query query = em.createQuery(consulta);
        return (Contratista) query.getSingleResult();
        } catch(NoResultException nre){
            return null;
        }
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ContratistaFacade() {
        super(Contratista.class);
    }
    
}
