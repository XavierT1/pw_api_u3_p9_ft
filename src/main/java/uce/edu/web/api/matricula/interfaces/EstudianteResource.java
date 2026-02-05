package uce.edu.web.api.matricula.interfaces;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import uce.edu.web.api.matricula.aplication.EstudianteService;
import uce.edu.web.api.matricula.aplication.HijoService;
import uce.edu.web.api.matricula.aplication.representation.EstudianteRepresentation;
import uce.edu.web.api.matricula.aplication.representation.Hijorepresentation;
import uce.edu.web.api.matricula.aplication.representation.LinkDto;
import uce.edu.web.api.matricula.domain.Estudiante;

import java.util.ArrayList;
import java.util.List;


@Path("/estudiantes")
@Produces(MediaType.APPLICATION_JSON)
public class EstudianteResource {
    @Inject
    EstudianteService estudianteService;
    @Inject
    HijoService hijoService;

    @Context
    private UriInfo uriInfo;

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"admin","user","docente"})
public List<EstudianteRepresentation> listarTodos() {
    List<Estudiante> lista = this.estudianteService.listarTodos();
    List<EstudianteRepresentation> listaRep = new ArrayList<>();
    for (Estudiante est : lista) {
        listaRep.add(this.construirLinks(this.estudianteService.toRepresentation(est)));
    }
    return listaRep;
}

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    //@PermitAll
    public EstudianteRepresentation consultarbyId(@PathParam("id") Integer id) {
        return this.construirLinks(this.estudianteService.consultarbyId(id));
    }

    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("admin")
    public Response registrar(EstudianteRepresentation estudiante) {
        this.estudianteService.crear(estudiante);
        return Response.status(Response.Status.CREATED).entity(estudiante).build();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed("admin")
    public Response actualizar(@PathParam("id") Integer id, EstudianteRepresentation estudiante) {
        this.estudianteService.actualizar(id,estudiante);
        return Response.status(209).entity(estudiante).build();
    }

    @PATCH
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed("admin")
    public void actualizarParcial(@PathParam("id") Integer id, EstudianteRepresentation estudiante) {
        this.estudianteService.actualizarParcial(id,estudiante);
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed("admin")
    public void eliminar(@PathParam("id") Integer id) {
        this.estudianteService.eliminar(id);
    }

    @GET
    @Path("/provincia/genero")
    @Produces(MediaType.APPLICATION_XML)
    @RolesAllowed("admin")
    public List<Estudiante> buscarPorProvincia(@QueryParam("provincia") String provincia, @QueryParam("genero") String genero) {
        System.out.println("ListarPorProvincia");
        return this.estudianteService.buscarPorProvincia(provincia, genero);
    }

    @GET
    @Path("/{id}/hijos")
    @RolesAllowed("admin")
    public List<Hijorepresentation> buscarPorIdEstudiante(@PathParam("id") Integer id) {
        return this.hijoService.buscarPorIdEstudiante(id);
    }

    private EstudianteRepresentation construirLinks (EstudianteRepresentation er) {
    String self = this.uriInfo.getBaseUriBuilder().path(EstudianteResource.class)
         .path(String.valueOf(er.id)).build().toString();

    String hijos = this.uriInfo.getBaseUriBuilder().path(EstudianteResource.class)
         .path(String.valueOf(er.id)).path("hijos").build().toString();

    er.links = List.of(new LinkDto(self, "self"), new LinkDto(hijos, "hijos"));

    return er;
    }

}