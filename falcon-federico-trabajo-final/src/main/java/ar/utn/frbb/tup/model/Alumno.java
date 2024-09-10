package ar.utn.frbb.tup.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class Alumno {
    private Integer id;
    private String nombre;
    private String apellido;
    private Integer dni;
    private List<Integer> asignaturas;

    public Alumno(Integer id, String nombre, String apellido, Integer dni, List<Integer> asignaturas) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.asignaturas = asignaturas;
    }

    public Alumno(){};

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "Alumno{" +
                "idAlumno=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", dni=" + dni +
                ", asignaturas=" + asignaturas +
                '}';
    }
}
