package ar.utn.frbb.tup.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Asignatura {
    private int id;
    private Materia materia;
    private EstadoAsignatura estado;
    private Integer nota;


    public Asignatura(int id, Materia materia, EstadoAsignatura estado, Integer nota) {
        this.id = id;
        this.materia = materia;
        this.estado = estado;
        this.nota = nota;
    }

    public Asignatura() {
    }

    @Override
    public int hashCode() {return super.hashCode();}

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "Asignatura{" +
                "id=" + id +
                ", materia=" + materia +
                ", estado=" + estado +
                ", nota=" + nota +
                '}';
    }
}
