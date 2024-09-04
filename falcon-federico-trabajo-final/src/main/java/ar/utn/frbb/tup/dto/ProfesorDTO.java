package ar.utn.frbb.tup.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProfesorDTO {
    private Integer id;
    private String nombre;
    private String apellido;
    private String titulo;

    // private List<Materia> materiasDictadas;

    public ProfesorDTO() {
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
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