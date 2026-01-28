package uce.edu.web.api.matricula.aplication;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uce.edu.web.api.matricula.aplication.representation.Estudianterepresentation;
import uce.edu.web.api.matricula.domain.Estudiante;
import uce.edu.web.api.matricula.infrastructure.EstudianteRepository;

import java.util.List;

@ApplicationScoped
public class EstudianteService {

    @Inject
    EstudianteRepository estudianteRepository;

    public List<Estudiante> listarTodos(){
        return estudianteRepository.listAll();
    }

    public Estudianterepresentation consultarbyId(Integer id){
        Estudiante est = estudianteRepository.findById(id.longValue());
        return toRepresentation(est);
    }

    @Transactional
    public void crear(Estudianterepresentation rep){
        estudianteRepository.persist(toEntity(rep));
    }

    @Transactional
    public void actualizar(Integer id, Estudianterepresentation rep){
        Estudiante estudiante = estudianteRepository.findById(id.longValue());

        estudiante.nombre = rep.nombre;
        estudiante.apellido = rep.apellido;
        estudiante.fechaNacimiento = rep.fechaNacimiento;
        estudiante.provincia = rep.provincia;
    }

    @Transactional
    public void actualizarParcial(Integer id, Estudianterepresentation rep){
        Estudiante estudiante = estudianteRepository.findById(id.longValue());

        if (rep.nombre != null)
            estudiante.nombre = rep.nombre;

        if (rep.apellido != null)
            estudiante.apellido = rep.apellido;

        if (rep.fechaNacimiento != null)
            estudiante.fechaNacimiento = rep.fechaNacimiento;

        if (rep.provincia != null)
            estudiante.provincia = rep.provincia;
    }

    @Transactional
    public void eliminar(Integer id){
        estudianteRepository.deleteById(id.longValue());
    }

    public List<Estudiante> buscarPorProvincia(String provincia, String genero) {
        return estudianteRepository
                .find("provincia = ?1 and genero = ?2", provincia, genero)
                .list();
    }

    // ==========================
    // MAPPERS
    // ==========================

    private Estudianterepresentation toRepresentation(Estudiante est) {
        Estudianterepresentation rep = new Estudianterepresentation();
        rep.id = est.id;
        rep.nombre = est.nombre;
        rep.apellido = est.apellido;
        rep.fechaNacimiento = est.fechaNacimiento;
        rep.provincia = est.provincia;
        return rep;
    }

    private Estudiante toEntity(Estudianterepresentation rep) {
        Estudiante est = new Estudiante();
        est.id = rep.id;
        est.nombre = rep.nombre;
        est.apellido = rep.apellido;
        est.fechaNacimiento = rep.fechaNacimiento;
        est.provincia = rep.provincia;
        return est;
    }
}
