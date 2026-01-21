package uce.edu.web.api.matricula.application;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uce.edu.web.api.matricula.domain.Materia;
import uce.edu.web.api.matricula.infraestructure.MateriaRepository;
import java.util.List;

@ApplicationScoped
public class MateriaService {

    @Inject
    private MateriaRepository materiaRepository;

    public List<Materia> listarTodos() {
        return this.materiaRepository.listAll();
    }

    public Materia consultarPorId(Integer id) {
        return this.materiaRepository.findById(id.longValue());
    }

    @Transactional
    public void crear(Materia materia){
        this.materiaRepository.persist(materia);
    }

    @Transactional
    public void actualizar(Integer id, Materia mat){
        Materia materia = this.consultarPorId(id);
        materia.nombre = mat.nombre;
        materia.creditos = mat.creditos;
        materia.codigo = mat.codigo;
        }
        
        @Transactional
        public void actualizarParcial(Integer id, Materia materia){
            Materia existente = this.consultarPorId(id);
            if(materia.nombre != null){
                existente.nombre = materia.nombre;
            }
            if(materia.creditos != null){
                existente.creditos = materia.creditos;
            }
            if(materia.codigo != null){
                existente.codigo = materia.codigo;
            }
        }
        
        @Transactional
        public void borrar(Integer id){
            this.materiaRepository.deleteById(id.longValue());
        }

        // --- MÉTODOS ADICIONALES ---

    // Consultar por Código
    public Materia consultarPorCodigo(String codigo) {
        return this.materiaRepository.buscarPorCodigo(codigo);
    }

    // Borrar por Código
    @Transactional
    public void borrarPorCodigo(String codigo) {
        this.materiaRepository.delete("codigo", codigo);
    }
}


