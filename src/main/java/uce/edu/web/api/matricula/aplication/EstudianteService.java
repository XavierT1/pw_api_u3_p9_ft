package uce.edu.web.api.matricula.aplication;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uce.edu.web.api.matricula.aplication.representation.EstudianteRepresentation;
import uce.edu.web.api.matricula.domain.Estudiante;
import uce.edu.web.api.matricula.infrastructure.EstudianteRepository;
import io.quarkus.panache.common.Sort;
import java.util.List;

@ApplicationScoped
public class EstudianteService {

    @Inject
    EstudianteRepository estudianteRepository;

    public List<Estudiante> listarTodos(){
        return estudianteRepository.listAll(Sort.by("id"));
    }

    public EstudianteRepresentation consultarbyId(Integer id){
        Estudiante est = estudianteRepository.findById(id.longValue());
        return (est != null) ? toRepresentation(est) : null;
    }

    @Transactional
    public void crear(EstudianteRepresentation rep){
        estudianteRepository.persist(toEntity(rep));
    }

    @Transactional
    public void actualizar(Integer id, EstudianteRepresentation rep){
        Estudiante estudiante = estudianteRepository.findById(id.longValue());
        if(estudiante != null){
        estudiante.nombre = rep.nombre;
        estudiante.apellido = rep.apellido;
        estudiante.fechaNacimiento = rep.fechaNacimiento;
        estudiante.provincia = rep.provincia;
        estudiante.genero = rep.genero;
        }  
    }

    @Transactional
    public void actualizarParcial(Integer id, EstudianteRepresentation rep){
        Estudiante estudiante = estudianteRepository.findById(id.longValue());
        
        if (estudiante != null) {
            if (rep.nombre != null)
                estudiante.nombre = rep.nombre;

            if (rep.apellido != null)
                estudiante.apellido = rep.apellido;

            if (rep.fechaNacimiento != null)
                estudiante.fechaNacimiento = rep.fechaNacimiento;

            if (rep.provincia != null)
                estudiante.provincia = rep.provincia;

            if (rep.genero != null)
                estudiante.genero = rep.genero;
        }
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

   public EstudianteRepresentation toRepresentation(Estudiante est) {
        if (est == null) return null;
        EstudianteRepresentation rep = new EstudianteRepresentation();
        rep.id = est.id;
        rep.nombre = est.nombre;
        rep.apellido = est.apellido;
        rep.fechaNacimiento = est.fechaNacimiento;
        rep.provincia = est.provincia;
        rep.genero = est.genero;
        return rep;
    }

    private Estudiante toEntity(EstudianteRepresentation rep) {
        Estudiante est = new Estudiante();
        est.id = rep.id;
        est.nombre = rep.nombre;
        est.apellido = rep.apellido;
        est.fechaNacimiento = rep.fechaNacimiento;
        est.provincia = rep.provincia;
        est.genero = rep.genero;
        return est;
    }
}
