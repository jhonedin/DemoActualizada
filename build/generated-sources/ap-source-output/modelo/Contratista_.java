package modelo;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Contratos;
import modelo.Ingreso;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-06-08T07:53:42")
@StaticMetamodel(Contratista.class)
public class Contratista_ { 

    public static volatile SingularAttribute<Contratista, Long> nitcontratista;
    public static volatile ListAttribute<Contratista, Ingreso> ingresoList;
    public static volatile SingularAttribute<Contratista, Integer> codigocontratista;
    public static volatile SingularAttribute<Contratista, String> nombrecontratista;
    public static volatile ListAttribute<Contratista, Contratos> contratosList;
    public static volatile SingularAttribute<Contratista, String> estadocontratista;

}