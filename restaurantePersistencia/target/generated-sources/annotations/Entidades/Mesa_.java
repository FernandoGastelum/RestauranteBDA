package Entidades;

import Entidades.Reserva;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-10-27T22:38:45", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Mesa.class)
public class Mesa_ { 

    public static volatile SingularAttribute<Mesa, String> tipo;
    public static volatile SingularAttribute<Mesa, String> ubicacion;
    public static volatile SingularAttribute<Mesa, String> codigo;
    public static volatile ListAttribute<Mesa, Reserva> reservas;
    public static volatile SingularAttribute<Mesa, Long> id;
    public static volatile SingularAttribute<Mesa, Integer> capacidad;

}