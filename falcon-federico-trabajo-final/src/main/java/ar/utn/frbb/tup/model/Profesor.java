package ar.utn.frbb.tup.model;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Profesor {
    private Integer id;
    private String nombre;
    private String apellido;
    private String titulo;

    // private List<Materia> materiasDictadas;

    public Profesor() {
    }

    @Override
    public String toString() {
        return
            "Profesor{" +
            "id=" + id +
            ", nombre='" + nombre + '\'' +
            ", apellido='" + apellido + '\'' +
            ", titulo='" + titulo + '\'' +
            '}';
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
