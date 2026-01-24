package uce.edu.web.api.matricula.interfaces;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import uce.edu.web.api.matricula.application.MateriaService;
import uce.edu.web.api.matricula.domain.Materia;

import java.util.List;

@Path("/materias")
@Produces(MediaType.APPLICATION_JSON)
public class MateriaResource {
    @Inject
    private MateriaService materiaService;

    @GET
    @Path("")
    public List<Materia> listarTodos() {
        return this.materiaService.listarTodos();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultarPorId(@PathParam("id") Integer ident) {
        Materia materia = this.materiaService.consultarPorId(ident);
        return Response.status(200).entity(materia).build();
    }

    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response guardar(Materia materia) {
        this.materiaService.crear(materia);
        return Response.status(209).entity(null).build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response actualizar(@PathParam("id") Integer id, Materia materia) {
        this.materiaService.actualizar(id, materia);
        return Response.status(200).entity(null).build();
    }

    @PATCH
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response actualizarParcial(@PathParam("id") Integer id, Materia materia) {
        this.materiaService.actualizarParcial(id, materia);
        return Response.status(200).entity(null).build();
    }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response borrar(@PathParam("id") Integer id) {
        this.materiaService.borrar(id);
        return Response.status(Response.Status.NOT_FOUND).entity(null).build();
    }

    // --- ENDPOINTS ADICIONALES ---

    // Consultar materia por código
    @GET
    @Path("/{codigo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultarPorCodigo(@PathParam("codigo") String codigo) {
        Materia materia = this.materiaService.consultarPorCodigo(codigo);
        return Response.status(Response.Status.NOT_FOUND).entity(materia).build();
    }

    // Borrar materia por código
    @DELETE
    @Path("/{codigo}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response borrarPorCodigo(@PathParam("codigo") String codigo) {
        this.materiaService.borrarPorCodigo(codigo);
        return Response.status(Response.Status.NOT_FOUND).entity(null).build();
    }

}