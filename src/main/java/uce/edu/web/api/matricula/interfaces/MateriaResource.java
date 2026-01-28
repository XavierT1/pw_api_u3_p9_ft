package uce.edu.web.api.matricula.interfaces;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import uce.edu.web.api.matricula.aplication.MateriaService;
import uce.edu.web.api.matricula.domain.Materia;

import java.util.List;

@Path("/materias")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MateriaResource {

    @Inject
    MateriaService materiaService;

    @GET
    @Path("")
    public List<Materia> listar() {
        return materiaService.listarTodos();
    }

    @GET
    @Path("/{id}")
    public Materia buscar(@PathParam("id") Integer id) {
        return materiaService.consultarById(id);
    }

    @POST
    @Path("")
    public Response crear(Materia materia) {
        materiaService.crear(materia);
        return Response
                .status(Response.Status.CREATED) // 201
                .entity(materia)
                .build();
    }

    @PUT
    @Path("/{id}")
    public Response actualizar(
            @PathParam("id") Integer id,
            Materia materia) {

        materiaService.actualizar(id, materia);

        return Response
                .status(Response.Status.OK) // 200
                .entity(materia)
                .build();
    }

    @PATCH
    @Path("/{id}")
    public Response actualizarParcial(
            @PathParam("id") Integer id,
            Materia materia) {

        materiaService.actualizarParcial(id, materia);

        return Response
                .status(Response.Status.OK)
                .entity(materia)
                .build();
    }

    @DELETE
    @Path("/{id}")
    public Response eliminar(@PathParam("id") Integer id) {
        materiaService.eliminar(id);

        return Response
                .status(Response.Status.NO_CONTENT) // 204
                .build();
    }

    @GET
    @Path("/activas")
    public Response listarActivas() {
        List<Materia> materias = materiaService.listarActivas();

        return Response
                .ok(materias) // 200
                .build();
    }

    @GET
    @Path("/buscar/{nombre}")
    public Response buscarPorNombre(
            @PathParam("nombre") String nombre) {

        List<Materia> materias = materiaService.buscarPorNombre(nombre);

        return Response
                .ok(materias)
                .build();
    }
}
