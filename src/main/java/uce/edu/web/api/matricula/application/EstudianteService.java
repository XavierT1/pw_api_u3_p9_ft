package uce.edu.web.api.matricula.application;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uce.edu.web.api.matricula.domain.Estudiante;
import uce.edu.web.api.matricula.infraestructure.EstudianteRepository;
import java.util.List;

@ApplicationScoped
public class EstudianteService {

    @Inject
    private EstudianteRepository estudianteRepository;

    public List<Estudiante> listarTodos() {
        return this.estudianteRepository.listAll();
    }

    public Estudiante consultarPorId(Integer id) {
        return this.estudianteRepository.findById(id.longValue());
    }

    @Transactional
    public void crear(Estudiante estu){
        this.estudianteRepository.persist(estu);
    }

    @Transactional
    public void actualizar(Integer id, Estudiante estu){
        Estudiante estudiante = this.consultarPorId(id);
        estudiante.apellido = estu.apellido;
        estudiante.nombre = estu.nombre;
        estudiante.fechaNacimiento = estu.fechaNacimiento;
        
        }
        
        @Transactional
        public void actualizarParcial(Integer id, Estudiante est){
            Estudiante estudiante = this.consultarPorId(id);
            if(est.nombre != null){
                estudiante.nombre = est.nombre;
            }
            if(est.apellido != null){
                estudiante.apellido = est.apellido;
            }
            if(est.fechaNacimiento != null){
                estudiante.fechaNacimiento = est.fechaNacimiento;
            }
        }
        
        @Transactional
        public void borrar(Integer id){
            this.estudianteRepository.deleteById(id.longValue());
        }
}


