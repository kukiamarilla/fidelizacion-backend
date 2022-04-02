package com.backend.fidelizacion.rest;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.backend.fidelizacion.ejb.ConceptoDAO;
import com.backend.fidelizacion.model.Concepto;

import org.json.JSONObject;

@Path("/conceptos")
@Consumes("application/json")
@Produces("application/json")
public class ConceptoController {

    @Inject
    private ConceptoDAO conceptoDAO;

    @GET
    public Response listar() {
        return Response.ok(conceptoDAO.listar()).build();
    }

    @POST
    public Response crear(Concepto c) {
        conceptoDAO.crear(c);
        return Response.ok(c).build();
    }

    @GET
    @Path("/{id}")
    public Response obtener(@PathParam("id") Long id) {
        return Response.ok(conceptoDAO.obtener(id)).build();
    }

    @PUT
    @Path("/{id}")
    public Response actualizar(@PathParam("id") Long id, Concepto c) {
        c.setId(id);
        conceptoDAO.actualizar(c);
        return Response.ok(c).build();
    }

    @DELETE
    @Path("/{id}")
    public Response eliminar(@PathParam("id") Long id) {
        conceptoDAO.eliminar(id);
        JSONObject response = new JSONObject();
        response.put("message", "Concepto eliminado");
        return Response.ok(response.toString()).build();
    }
}
