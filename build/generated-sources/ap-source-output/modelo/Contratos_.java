package modelo;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Contratista;
import modelo.Empleado;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-07-13T21:13:51")
@StaticMetamodel(Contratos.class)
public class Contratos_ { 

    public static volatile SingularAttribute<Contratos, Contratista> codigocontratista;
    public static volatile SingularAttribute<Contratos, Date> fechaingreso;
    public static volatile SingularAttribute<Contratos, Date> fechasalida;
    public static volatile SingularAttribute<Contratos, Empleado> codigoempleado;
    public static volatile SingularAttribute<Contratos, Integer> numerocontrato;

}