package cl.ipchile.jira.entity;

import cl.ipchile.jira.entity.Usuario;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2024-08-31T14:54:58")
@StaticMetamodel(Tarea.class)
public class Tarea_ { 

    public static volatile SingularAttribute<Tarea, String> descripcion;
    public static volatile SingularAttribute<Tarea, String> estado;
    public static volatile SingularAttribute<Tarea, String> titulo;
    public static volatile SingularAttribute<Tarea, Usuario> usuario;
    public static volatile SingularAttribute<Tarea, Long> id;
    public static volatile SingularAttribute<Tarea, String> prioridad;

}