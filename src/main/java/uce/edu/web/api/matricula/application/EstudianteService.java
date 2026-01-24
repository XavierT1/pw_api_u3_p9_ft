package uce.edu.web.api.matricula.application;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uce.edu.web.api.matricula.application.representation.EstudianteRepresentation;
import uce.edu.web.api.matricula.domain.Estudiante;
import uce.edu.web.api.matricula.infraestructure.EstudianteRepository;
import java.util.List;

@ApplicationScoped
public class EstudianteService {

    @Inject
    private EstudianteRepository estudianteRepository;

    public List<EstudianteRepresentation> listarTodos() {
        List<EstudianteRepresentation> list = new ArrayList<>();
        for(Estudiante estu : this.estudianteRepository.listAll()){
            list.add(this.mapperToER(estu));
        }
        return list;
    }

    public EstudianteRepresentation consultarPorId(Integer id) {
        return this.mapperToEstudiante(this.estudianteRepository.findById(id.longValue()));
    }

    @Transactional
    public void crear(EstudianteRepresentation estu){
        this.estudianteRepository.persist(this.mapperToEstudiante(estu));
    }

    @Transactional
    public void actualizar(Integer id, EstudianteRepresentation estu){
        Estudiante estudiante = this.mapperToEstudiante(this.consultarPorId(id));
        estudiante.apellido = estu.apellido;
        estudiante.nombre = estu.nombre;
        estudiante.fechaNacimiento = estu.fechaNacimiento;
        
        }
        
        @Transactional
        public void actualizarParcial(Integer id, EstudianteRepresentation est){
            Estudiante estudiante = this.mapperToEstudiante(this.consultarPorId(id));
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

        public List<EstudianteRepresentation> buscarPorProvincia(String provincia, String genero) {
            return this.estudianteRepository.find("provincia = ?1 and genero = ?2", provincia,genero).list();
        }

         private Estudiante mapperToER(Estudiante est){
            Estudiante estu = new Estudiante();
            estu.id = est.id;
            estu.nombre = est.nombre;
            estu.apellido = est.apellido;
            estu.fechaNacimiento = est.fechaNacimiento;
            estu.provincia = est.provincia;
            estu.genero = est.genero;
            return estu;
         }

        private Estudiante mapperToEstudiante(Estudiante est){
            Estudiante estuR = new Estudiante();
            estuR.id = est.id;
            estuR.nombre = est.nombre;
            estuR.apellido = est.apellido;
            estuR.fechaNacimiento = est.fechaNacimiento;
            estuR.provincia = est.provincia;
            estuR.genero = est.genero;
            return estuR;
        }
}


