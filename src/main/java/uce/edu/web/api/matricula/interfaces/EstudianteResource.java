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
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import uce.edu.web.api.matricula.application.EstudianteService;
import uce.edu.web.api.matricula.domain.Estudiante;

import java.util.List;


@Path("/estudiantes")
@Produces(MediaType.APPLICATION_JSON)
public class EstudianteResource {
    @Inject
    private EstudianteService estudianteService;

    @GET
    @Path("/todos")
    public List<Estudiante> listarTodos() {
        return this.estudianteService.listarTodos();
    }

    @GET
    @Path("/consultarPorId/{id}")
    public Estudiante consultarPorId(@PathParam("id")Integer ident) {
        return this.estudianteService.consultarPorId(ident);
    }
    @POST
    @Path("/crear")
    public void guardar(Estudiante estu){
        this.estudianteService.crear(estu);
    }

    @PUT
    @Path("/actualizar/{id}")
    public void actualizar(@PathParam("id")Integer id, Estudiante estu){
        this.estudianteService.actualizar(id, estu);
    }

    @PATCH
    @Path("/actualizarParcial/{id}")
    public void actualizarParcial(@PathParam("id")Integer id, Estudiante estu){
        this.estudianteService.actualizarParcial(id, estu);
    }

    @DELETE
    @Path("/borrar/{id}")
    public void borrar(@PathParam("id")Integer id){
        this.estudianteService.borrar(id);
    }

    @GET
    @Path("/buscarPorProvincia")
    public List<Estudiante> buscarPorProvincia(@QueryParam("provincia") String provincia, @QueryParam("genero") String genero) {
        return this.estudianteService.buscarPorProvincia(provincia, genero);
    }
}