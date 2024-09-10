package ar.utn.frbb.tup.model;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class Profesor {
    private int id;
    private String nombre;
    private String apellido;
    private String titulo;

    private List<Integer> materiasDictadas;

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
        return this.id;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
