package uce.edu.web.api.matricula.aplication;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uce.edu.web.api.matricula.domain.Materia;
import uce.edu.web.api.matricula.infrastructure.MateriaRepository;

import java.util.List;

@ApplicationScoped
public class MateriaService {

    @Inject
    MateriaRepository materiaRepository;

    public List<Materia> listarTodos() {
        return this.materiaRepository.listAll();
    }

    public Materia consultarById(Integer id) {
        return this.materiaRepository.findById(id.longValue());
    }

    @Transactional
    public void crear(Materia materia) {
        this.materiaRepository.persist(materia);
    }

    @Transactional
    public void actualizar(Integer id, Materia mat) {
        Materia materia = this.consultarById(id);

        materia.nombre = mat.nombre;
        materia.creditos = mat.creditos;
        materia.activa = mat.activa;
    }

    @Transactional
    public void actualizarParcial(Integer id, Materia mat) {
        Materia materia = this.consultarById(id);

        if (mat.nombre != null) {
            materia.nombre = mat.nombre;
        }

        if (mat.creditos != null) {
            materia.creditos = mat.creditos;
        }

        if (mat.activa != null) {
            materia.activa = mat.activa;
        }
    }

    @Transactional
    public void eliminar(Integer id) {
        this.materiaRepository.deleteById(id.longValue());
    }

    @Transactional
    public void cambiarEstadoActivo(Integer id, Boolean estado) {
        Materia materia = this.consultarById(id);
        materia.activa = estado;
    }

    public List<Materia> listarActivas() {
        return materiaRepository.listarActivas();
    }

    public List<Materia> buscarPorNombre(String nombre) {
        return materiaRepository.buscarPorNombre(nombre);
    }
}
