package uce.edu.web.api.matricula.aplication;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import uce.edu.web.api.matricula.aplication.representation.Hijorepresentation;
import uce.edu.web.api.matricula.domain.Hijo;
import uce.edu.web.api.matricula.infrastructure.HijoRepository;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class HijoService {
    @Inject
    private HijoRepository hijoRepository;

    public List<Hijorepresentation>buscarPorIdEstudiante(Integer idEstudiante) {
        List<Hijorepresentation> lista = new ArrayList<>();
        for (Hijo h : this.hijoRepository.buscarPorIdEstudiante(idEstudiante)) {
            lista.add(mapperToHijoRepresentation(h));
        }
        return lista;
    }

    private Hijorepresentation mapperToHijoRepresentation(Hijo hijo) {
        Hijorepresentation hr = new Hijorepresentation();
        hr.id = hijo.id;
        hr.nombre = hijo.nombre;
        hr.apellido = hijo.apellido;
        return hr;
    }
}
