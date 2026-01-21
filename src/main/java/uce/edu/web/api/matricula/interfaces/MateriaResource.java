package uce.edu.web.api.matricula.interfaces;

import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import uce.edu.web.api.matricula.application.MateriaService;
import uce.edu.web.api.matricula.domain.Materia;

import java.util.List;


@Path("/materias")
@Produces(MediaType.APPLICATION_JSON)
public class MateriaResource {
    @Inject
    private MateriaService materiaService;
    @GET
    @Path("/todos")
    public List<Materia> listarTodos() {
        return this.materiaService.listarTodos();
    }

    @GET
    @Path("/consultarPorId/{id}")
    public Materia consultarPorId(@PathParam("id")Integer ident) {
        return this.materiaService.consultarPorId(ident);
    }
    @POST
    @Path("/crear")
    public void guardar(Materia materia){
        this.materiaService.crear(materia);
    }

    @PUT
    @Path("/actualizar/{id}")
    public void actualizar(@PathParam("id")Integer id, Materia materia){
        this.materiaService.actualizar(id, materia);
    }

    @PATCH
    @Path("/actualizarParcial/{id}")
    public void actualizarParcial(@PathParam("id")Integer id, Materia materia){
        this.materiaService.actualizarParcial(id, materia);
    }

    @DELETE
    @Path("/borrar/{id}")
    public void borrar(@PathParam("id")Integer id){
        this.materiaService.borrar(id);
    }

    // --- ENDPOINTS ADICIONALES ---

    // Consultar materia por código
    @GET
    @Path("/consultarPorCodigo/{codigo}")
    public Materia consultarPorCodigo(@PathParam("codigo") String codigo) {
        return this.materiaService.consultarPorCodigo(codigo);
    }

    // Borrar materia por código
    @DELETE
    @Path("/borrarPorCodigo/{codigo}")
    public void borrarPorCodigo(@PathParam("codigo") String codigo) {
        this.materiaService.borrarPorCodigo(codigo);
    }

}