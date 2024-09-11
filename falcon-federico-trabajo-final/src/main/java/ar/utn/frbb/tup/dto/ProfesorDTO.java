package ar.utn.frbb.tup.dto;

import ar.utn.frbb.tup.model.Materia;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ProfesorDTO {
    private Integer id;
    private String nombre;
    private String apellido;
    private String titulo;

    private List<Integer> materiasDictadas;

    public ProfesorDTO() {
    }

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
        return
            "ProfesorDTO{" +
            "id=" + id +
            ", nombre='" + nombre + '\'' +
            ", apellido='" + apellido + '\'' +
            ", titulo='" + titulo + '\'' +
            '}';
    }
}