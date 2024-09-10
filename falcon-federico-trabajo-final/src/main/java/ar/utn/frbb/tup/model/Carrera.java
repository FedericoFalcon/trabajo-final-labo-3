package ar.utn.frbb.tup.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Setter
@Getter
public class Carrera {
    private String nombre;
    private Integer cantidadAnios;

    @JsonIgnore
    private List<Materia> materias;

    public Carrera(String nombre, Integer cantidadAnios, List<Materia> materias) {
        this.nombre = nombre;
        this.cantidadAnios = cantidadAnios;
        this.materias = materias;
    }

    public Carrera() {}

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return "Carrera{" +
                "nombre='" + nombre + '\'' +
                ", cantidadAnios=" + cantidadAnios +
                ", materias=" + materias +
                '}';
    }
}
