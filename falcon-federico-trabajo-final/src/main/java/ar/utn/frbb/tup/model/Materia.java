package ar.utn.frbb.tup.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Materia {
    private int id;
    private String nombre;
    private int anio;
    private int cuatrimestre;
    private Integer profesorId;

    private List<Integer> correlatividades;

    public Materia(){}

    public Materia(String nombre, int anio, int cuatrimestre, Integer profesorId) {
        this.anio = anio;
        this.cuatrimestre = cuatrimestre;
        this.nombre = nombre;
        this.profesorId = profesorId;

        this.correlatividades = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Materia{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", anio=" + anio +
                ", cuatrimestre=" + cuatrimestre +
                ", profesorId=" + profesorId +
                ", correlatividades=" + correlatividades +
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
