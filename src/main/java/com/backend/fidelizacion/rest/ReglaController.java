
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

import com.backend.fidelizacion.ejb.ReglaDAO;
import com.backend.fidelizacion.model.Regla;

import org.json.JSONObject;

@Path("/reglas")
@Consumes("application/json")
@Produces("application/json")
public class ReglaController {

    @Inject
    private ReglaDAO reglaDAO;

    @GET
    public Response listar() {
        return Response.ok(reglaDAO.listar()).build();
    }

    @GET
    @Path("/{id}")
    public Response obtener(@PathParam("id") Long id) {
        return Response.ok(reglaDAO.obtener(id)).build();
    }

    @POST
    public Response crear(Regla regla) {
        reglaDAO.crear(regla);
        if(!regla.isValid()) {
            JSONObject mensaje = new JSONObject();
            mensaje.put("mensaje", "Límite superior debe ser mayor al límite inferior");
            return Response.status(Response.Status.BAD_REQUEST).entity(mensaje.toString()).build();
        }
        return Response.ok(regla).build();
    }

    @PUT
    @Path("/{id}")
    public Response actualizar(@PathParam("id") Long id, Regla regla) {
        Regla current = reglaDAO.obtener(id);
        current.setLimiteInferior(regla.getLimiteInferior());
        current.setLimiteSuperior(regla.getLimiteSuperior());
        current.setMonto(regla.getMonto());
        if(!current.isValid()) {
            JSONObject mensaje = new JSONObject();
            mensaje.put("mensaje", "Límite superior debe ser mayor al límite inferior");
            return Response.status(Response.Status.BAD_REQUEST).entity(mensaje.toString()).build();
        }
        reglaDAO.actualizar(current);
        return Response.ok(current).build();
    }

    @DELETE
    @Path("/{id}")
    public Response eliminar(@PathParam("id") Long id) {
        reglaDAO.eliminar(id);
        JSONObject mensaje = new JSONObject();
        mensaje.put("mensaje", "Regla eliminada");
        return Response.ok(mensaje.toString()).build();
    }
}

