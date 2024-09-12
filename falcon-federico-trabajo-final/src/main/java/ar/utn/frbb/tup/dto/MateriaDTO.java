package ar.utn.frbb.tup.dto;

import ar.utn.frbb.tup.model.Materia;
import ar.utn.frbb.tup.model.Profesor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Setter
@Getter
public class MateriaDTO {
    private static final AtomicInteger contadorId = new AtomicInteger(1); // Contador estático

    private int id;
    private String nombre;
    private int anio;
    private int cuatrimestre;
    private Integer profesorId;

    private List<Integer> correlatividades;

    public MateriaDTO(){
        this.id = contadorId.getAndIncrement();
    }

    @Override
    public String toString() {
        return "MateriaDTO{" +
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
