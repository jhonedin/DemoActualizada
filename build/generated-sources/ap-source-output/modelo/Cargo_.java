package modelo;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Empleado;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-06-08T07:53:42")
@StaticMetamodel(Cargo.class)
public class Cargo_ { 

    public static volatile SingularAttribute<Cargo, String> nombrecargo;
    public static volatile ListAttribute<Cargo, Empleado> empleadoList;
    public static volatile SingularAttribute<Cargo, Integer> valorhoracargo;
    public static volatile SingularAttribute<Cargo, Integer> codigocargo;

}