package modelo;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Contratista;
import modelo.Empleado;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-07-13T21:13:51")
@StaticMetamodel(Ingreso.class)
public class Ingreso_ { 

    public static volatile SingularAttribute<Ingreso, Contratista> contratistaingreso;
    public static volatile SingularAttribute<Ingreso, Integer> numeroingreso;
    public static volatile SingularAttribute<Ingreso, Empleado> empleadoingreso;
    public static volatile SingularAttribute<Ingreso, Date> fechaingreso;
    public static volatile SingularAttribute<Ingreso, Date> horasalidaingreso;
    public static volatile SingularAttribute<Ingreso, Date> horaentradaingreso;
    public static volatile SingularAttribute<Ingreso, String> autorizadoingreso;

}