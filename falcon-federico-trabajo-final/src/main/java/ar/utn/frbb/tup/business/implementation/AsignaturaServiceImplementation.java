package ar.utn.frbb.tup.business.implementation;

import ar.utn.frbb.tup.business.AsignaturaService;
import ar.utn.frbb.tup.model.Asignatura;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AsignaturaServiceImplementation implements AsignaturaService {

    @Override
    public Asignatura getAsignaturaByName(List<Asignatura> listaAsignaturas, String nombreAsignatura) {
        for (Asignatura asignatura: listaAsignaturas) {
            if (nombreAsignatura.equalsIgnoreCase(asignatura.getMateria().getNombre())) {
                return asignatura;
            }
        }
        return null;
    }
}
