package cl.ipchile.jira.entity;

import cl.ipchile.jira.entity.Tarea;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2024-08-31T14:54:58")
@StaticMetamodel(Usuario.class)
public class Usuario_ { 

    public static volatile SingularAttribute<Usuario, String> contrasena;
    public static volatile SingularAttribute<Usuario, Long> id;
    public static volatile ListAttribute<Usuario, Tarea> tareas;
    public static volatile SingularAttribute<Usuario, String> nombre;
    public static volatile SingularAttribute<Usuario, Integer> edad;
    public static volatile SingularAttribute<Usuario, String> email;

}