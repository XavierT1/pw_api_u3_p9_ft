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
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import uce.edu.web.api.matricula.application.EstudianteService;
import uce.edu.web.api.matricula.application.HijoService;
import uce.edu.web.api.matricula.domain.Estudiante;
import uce.edu.web.api.matricula.domain.Hijo;

import java.util.List;

@Path("/estudiantes")
@Produces(MediaType.APPLICATION_JSON)
public class EstudianteResource {
    @Inject
    private EstudianteService estudianteService;
    @Inject
    private HijoService hijoService;

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Estudiante> listarTodos() {
        System.out.println("ListarTODOSXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        return this.estudianteService.listarTodos();
    }

    @GET
    @Path("/provincia/genero")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Estudiante> buscarPorProvincia(@QueryParam("provincia") String provincia,
            @QueryParam("genero") String genero) {
        System.out.println("LISTAR POR PROVINCIA Y GENERO XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");

        return this.estudianteService.buscarPorProvincia(provincia, genero);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_XML)
    public Response consultarPorId(@PathParam("id") Integer ident) {
        Estudiante estu = this.estudianteService.consultarPorId(ident);
        return Response.status(200).entity(estu).build();
    }

    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response guardar(Estudiante estu) {
        this.estudianteService.crear(estu);
        return Response.status(Response.Status.CREATED).entity(estu).build();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes
    public Response actualizar(@PathParam("id") Integer id, Estudiante estu) {
        this.estudianteService.actualizar(id, estu);
        return Response.status(Response.Status.OK).entity(estu).build();    
    }

    @PATCH
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void actualizarParcial(@PathParam("id") Integer id, Estudiante estu) {
        this.estudianteService.actualizarParcial(id, estu);
        
    }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response borrar(@PathParam("id") Integer id) {
        this.estudianteService.borrar(id);
        return Response.status(Response.Status.NOT_FOUND).entity(null).build();
    }

    @GET
    @Path("/{id}/hijos")
    public List<Hijo> buscarHijosPorEstudiante(@PathParam("id") Integer id) {
        return this.hijoService.buscarPorIdEstudiante(id);
    }
}