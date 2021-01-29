package modelo;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Cargo;
import modelo.Contratos;
import modelo.Ingreso;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-07-13T21:13:51")
@StaticMetamodel(Empleado.class)
public class Empleado_ { 

    public static volatile ListAttribute<Empleado, Ingreso> ingresoList;
    public static volatile SingularAttribute<Empleado, String> telefonoempleado;
    public static volatile SingularAttribute<Empleado, Integer> codigoempleado;
    public static volatile SingularAttribute<Empleado, String> nombreempleado;
    public static volatile ListAttribute<Empleado, Contratos> contratosList;
    public static volatile SingularAttribute<Empleado, Long> cedulaempleado;
    public static volatile SingularAttribute<Empleado, String> estadoempleado;
    public static volatile SingularAttribute<Empleado, String> apellidoempleado;
    public static volatile SingularAttribute<Empleado, Cargo> codigocargo;

}