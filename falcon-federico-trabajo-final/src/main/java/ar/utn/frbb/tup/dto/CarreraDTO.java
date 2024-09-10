package ar.utn.frbb.tup.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarreraDTO {
    private String nombre;
    private Integer cantidadAnios;


    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return "Carrera{" +
                "nombre='" + nombre + '\'' +
                ", cantidadAnios=" + cantidadAnios +
                '}';
    }
}
