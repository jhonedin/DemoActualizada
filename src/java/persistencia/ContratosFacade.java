/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modelo.Contratos;

/**
 *
 * @author Jhon
 */
@Stateless
public class ContratosFacade extends AbstractFacade<Contratos> implements ContratosFacadeLocal {

    @PersistenceContext(unitName = "DemoActualizadaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ContratosFacade() {
        super(Contratos.class);
    }
    
}
