package ar.utn.frbb.tup.dto;

// import ar.utn.frbb.tup.model.Carrera;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Setter
@Getter
public class AlumnoDTO {
    private static final AtomicInteger contadorId = new AtomicInteger(1);

    private Integer id;
    private String nombre;
    private String apellido;
    private Integer dni;

    private List<Integer> materias;

    public AlumnoDTO() {
        this.id = contadorId.getAndIncrement();
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
        return "AlumnoDTO{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", dni=" + dni +
                ", asignaturas=" + materias +
                '}';
    }
}
