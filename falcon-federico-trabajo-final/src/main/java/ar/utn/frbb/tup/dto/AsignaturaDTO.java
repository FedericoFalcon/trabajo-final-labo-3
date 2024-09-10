package ar.utn.frbb.tup.dto;

import ar.utn.frbb.tup.model.EstadoAsignatura;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AsignaturaDTO {
    private int id;
    private EstadoAsignatura estado;
    private Integer nota;

    public AsignaturaDTO(int id, EstadoAsignatura estado, Integer nota) {
        this.id = id;
        this.estado = estado;
        this.nota = nota;
    }

    public AsignaturaDTO() {
    }
}
