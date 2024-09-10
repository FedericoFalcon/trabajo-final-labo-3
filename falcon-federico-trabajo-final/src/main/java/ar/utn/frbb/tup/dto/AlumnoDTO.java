package ar.utn.frbb.tup.dto;

// import ar.utn.frbb.tup.model.Carrera;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AlumnoDTO {
    private Integer id;
    private String nombre;
    private String apellido;
    private Integer dni;

    @Override
    public int hashCode() {
        return this.id;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "AlumnoDto{" +
                "idAlumno=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", dni=" + dni +
                '}';
    }
}
