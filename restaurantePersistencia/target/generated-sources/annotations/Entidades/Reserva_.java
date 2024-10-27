package Entidades;

import Entidades.Cliente;
import Entidades.Mesa;
import java.time.LocalDateTime;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-10-26T23:22:05", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Reserva.class)
public class Reserva_ { 

    public static volatile SingularAttribute<Reserva, Cliente> cliente;
    public static volatile SingularAttribute<Reserva, Boolean> estado;
    public static volatile SingularAttribute<Reserva, Integer> numeroPersonas;
    public static volatile SingularAttribute<Reserva, Double> costo;
    public static volatile SingularAttribute<Reserva, Mesa> mesa;
    public static volatile SingularAttribute<Reserva, LocalDateTime> fechaHora;
    public static volatile SingularAttribute<Reserva, String> lugar;
    public static volatile SingularAttribute<Reserva, Long> id;

}