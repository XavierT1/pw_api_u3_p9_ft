package uce.edu.web.api.matricula.infrastructure;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import uce.edu.web.api.matricula.domain.Materia;

import java.util.List;

@ApplicationScoped
public class MateriaRepository implements PanacheRepository<Materia> {
    public List<Materia> listarActivas() {
        return list("activa = true");
    }

    public List<Materia> buscarPorNombre(String nombre) {
        return find("LOWER(nombre) LIKE ?1",
                "%" + nombre.toLowerCase() + "%").list();
    }
}
